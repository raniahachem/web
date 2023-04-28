<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Entity\Client;
use App\Form\ReclamationType;
use App\Repository\ReclamationRepository;
use App\Repository\ClientRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;
use PHPMailer\PHPMailer\PHPMailer;


#[Route('/')]
class RecController extends AbstractController
{
   

    /*#[Route('/stat' , name:'stat')]
    public function stat(ReclamationRepository $repository)
    {
        $newReclamationCount = $repository->findNewReclamation();
        $reclamationTechniqueCount = $repository->findReclamationsByType('technique');
        $reclamationServiceCount = $repository->findReclamationsByType('service');
        $reclamationAutreCount = $repository->findReclamationsByType('autre');
        
        // Modification : récupérer les données à partir de la base de données
        $reclamationCounts = [        ['Type', 'Nombre'],
            ['Reclamations techniques', $reclamationTechniqueCount],
            ['Reclamations de service', $reclamationServiceCount],
            ['Autres reclamations', $reclamationAutreCount],
        ];
        
        $StatChart1 = new PieChart();
        $StatChart1->getData()->setArrayToDataTable($reclamationCounts);
        
        $StatChart1->getOptions()->setTitle("Statistiques des réclamations par type");
        $StatChart1->getOptions()->setHeight(400);
        $StatChart1->getOptions()->setIs3D(2);
        $StatChart1->getOptions()->setWidth(550);
        $StatChart1->getOptions()->getTitleTextStyle()->setBold(true);
        $StatChart1->getOptions()->getTitleTextStyle()->setColor('#009900');
        $StatChart1->getOptions()->getTitleTextStyle()->setItalic(true);
        $StatChart1->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $StatChart1->getOptions()->getTitleTextStyle()->setFontSize(15);
        
        return $this->render('rec/stat.html.twig', [
            'reclamation_counts' => $reclamationCounts, // Modification : passer les données à Twig
            'chart' => $StatChart1, // Modification : passer l'objet PieChart à Twig
        ]);    
    }*/


    #[Route('/stat', name: 'stat')]
public function stat(ReclamationRepository $repository)
{
    $newReclamationCount = $repository->findNewReclamation();
    $reclamationTechniqueCount = $repository->findReclamationsByType('technique');
    $reclamationServiceCount = $repository->findReclamationsByType('service');
    $reclamationAutreCount = $repository->findReclamationsByType('autre');

    $reclamationCounts = [
        ['Type', 'Nombre'],
        ['Technique', $reclamationTechniqueCount],
        ['Service', $reclamationServiceCount],
        ['Autre', $reclamationAutreCount],
    ];

    return $this->render('rec/stat.html.twig', [
        'reclamation_counts' => json_encode($reclamationCounts),
    ]);
}

    
    #[Route('/aff', name: 'app_rec_index')]
    public function index(Request $request, ReclamationRepository $reclamationRepository, PaginatorInterface $paginator): Response
{  
    $reclamations = $reclamationRepository->createQueryBuilder('r')
        ->orderBy('r.date_r', 'DESC')
        ->getQuery()
        ->getResult();

    $reclamations = $paginator->paginate(
        $reclamations,
        $request->query->getInt('page', 1),
        4
    );

    return $this->render('rec/index.html.twig', [
        'reclamations' => $reclamations,
    ]);
}


    #[Route('/recsansreponse', name: 'app_rec_reponse')]
    public function getUnansweredReclamations(ReclamationRepository $reclamationRepository): Response
{
    $reclamations = $reclamationRepository->findunansweredRec();

    return $this->render('rec/sansreponse.html.twig', [
        'reclamations' => $reclamations
    ]);
}
#[Route('/trierpardate', name: 'app_rec_trier')]
public function trierParDate(Request $request, ReclamationRepository $reclamationRepository): Response
{
    $reclamations = $reclamationRepository->findBy([], ['date_r' => 'DESC']);

    return $this->render('rec/index.html.twig', [
        'reclamations' => $reclamations,
    ]);
}

#[Route('/order_By_Type', name: 'order_By_Type', methods: ['GET'])]
public function order_By_Type(ReclamationRepository $reclamationRepository): Response
{
    $reclamationByType = $reclamationRepository->orderByType();

    return $this->render('rec/index.html.twig', [
        'reclamations' => $reclamationByType,
    ]);
}



#[Route('/order_By_Date', name: 'order_By_Date', methods: ['GET'])]
public function order_By_Date(ReclamationRepository $reclamationRepository): Response
{

    $reclamationByDate = $reclamationRepository->order_By_Date();

    return $this->render('rec/index.html.twig', [
        'reclamations' => $reclamationByDate,
    ]);

}

    

    /*#[Route('/addrec', name: 'app_rec_new', methods: ['GET', 'POST'])]
    public function new(Request $request, ReclamationRepository $reclamationRepository): Response
    {
        $reclamation = new Reclamation();
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            //getSession userid
            $reclamation->setIdClient(3);
            $reclamationRepository->save($reclamation, true);

            return $this->redirectToRoute('app_rec_show', ['id' => $reclamation->getId()]
            , Response::HTTP_SEE_OTHER);
            
        }

        return $this->renderForm('rec/new.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form,
        ]);
    }*/


    #[Route('/addrec/{clientId}', name: 'app_rec_new', methods: ['GET', 'POST'])]
public function new(Request $request, int $clientId): Response
{
    $reclamation = new Reclamation();
    $form = $this->createForm(ReclamationType::class, $reclamation);
    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        $entityManager = $this->getDoctrine()->getManager();

        // Récupérer l'entité Client correspondante à partir de l'ID client
        $client = $this->getDoctrine()->getRepository(Client::class)->find($clientId);

        // Affecter l'entité Client à la propriété IdClient de la réclamation
        $reclamation->setIdClient($client);

        // Sauvegarder l'entité Reclamation
        $entityManager->persist($reclamation);
        $entityManager->flush();

    

    $mailer = new PHPMailer();
    $mailer->isSMTP();
    $mailer->SMTPSecure = 'tls';
    $mailer->SMTPAutoTLS = false;
    $mailer->Host = 'smtp.gmail.com';
    $mailer->Port = 587;
    $mailer->SMTPAuth = true;
    $mailer->Username = 'autoxpress2023@gmail.com';
    $mailer->Password = 'cjotybjburaneepj    ';
    $mailer->setFrom('Autoxpress');
    $mailer->addAddress($client->getEmailClient());
   
    
    $mailer->Subject = 'Reclamation';
$mailer->Body = 'Votre réclamation de type ' . $reclamation->getType() . ' a été prise en considération. 
Nous vous répondrons au plus vite, pour cela, veuillez checker la rubrique "mes anciennes réclamations" depuis notre site web.

Cordialement,
L\'équipe de service client';

    
    if (!$mailer->send()) {
        // Gestion des erreurs d'envoi de l'e-mail
        $this->addFlash('danger', 'Une erreur est survenue lors de l\'envoi de l\'e-mail de notification.');
    } else {
        // Succès de l'envoi de l'e-mail
        $this->addFlash('success', 'L\'événement a été supprimé avec succès et un e-mail de notification a été envoyé.');
    }

    
    return $this->redirectToRoute('app_rec_showclient', ['id' => $reclamation->getId()]);
    }
    return $this->renderForm('rec/new.html.twig', [
        'reclamation' => $reclamation,
        'form' => $form,
    ]);
}

    
    #[Route('/{id}', name: 'app_rec_show', methods: ['GET'])]
    public function show(Reclamation $reclamation): Response
    {
        return $this->render('rec/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }

    #[Route('/recpourclient/{id}', name: 'app_rec_showclient', methods: ['GET'])]
    public function showpourclient(Reclamation $reclamation): Response
    {
        return $this->render('rec/showclient.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_rec_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Reclamation $reclamation, ReclamationRepository $reclamationRepository): Response
    {
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reclamationRepository->save($reclamation, true);

            return $this->redirectToRoute('app_rec_new', ['clientId' => $reclamation->getIdClient()->getId()]);
        }

        return $this->renderForm('rec/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_rec_delete', methods: ['POST'])]
    public function delete(Request $request, Reclamation $reclamation, ReclamationRepository $reclamationRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reclamation->getId(), $request->request->get('_token'))) {
            $reclamationRepository->remove($reclamation, true);
        }

        return $this->redirectToRoute('app_rec_index', [], Response::HTTP_SEE_OTHER);
    }
    #[Route('/del/{id}', name: 'app_rec_deleteclient', methods: ['POST'])]
public function deletepourclient(Request $request, Reclamation $reclamation, ReclamationRepository $reclamationRepository): Response
{
    if ($this->isCsrfTokenValid('delete'.$reclamation->getId(), $request->request->get('_token'))) {
        $reclamationRepository->remove($reclamation, true);
    }

    return $this->redirectToRoute('app_rec_new', ['clientId' => $reclamation->getIdClient()->getId()]);
}



    #[Route('/searchRec', name: 'searchRec')]
    public function searchRec(Request $request, ReclamationRepository $repository)
    {
        $requestString = $request->get('searchValue');
        $Reclamations = $repository->findbytype($requestString);
    
        return $this->render('rec/index.html.twig', [
            'reclamations' => $Reclamations,
        ]);
    }
    


    #[Route('/affrecclient/{id}', name: 'app_rec_indexclient')]
public function afficher(Client $client, ReclamationRepository $reclamationRepository): Response
{
    $client = $this->getDoctrine()->getRepository(Client::class)->find(3);
$reclamations = $client->getReclamations();

    
    return $this->render('rec/indexclient.html.twig', [
        'reclamations' => $reclamations,
    ]);
}




}
