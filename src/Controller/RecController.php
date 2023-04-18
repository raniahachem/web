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
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Normalizer\Normalizer;
use Knp\Component\Pager\PaginatorInterface;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use PHPMailer\PHPMailer\PHPMailer;

#[Route('/')]
class RecController extends AbstractController
{
    #[Route('/aff', name: 'app_rec_index', methods: ['GET'])]
    public function index(ReclamationRepository $reclamationRepository): Response
    {
        return $this->render('rec/index.html.twig', [
            'reclamations' => $reclamationRepository->findAll(),
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
    $mailer->Username = 'rania.hachem@esprit.tn';
    $mailer->Password = '201JFT4388';
    $mailer->setFrom('E-Fit');
    $mailer->addAddress($client->getEmailClient());
   
    
    $mailer->Subject = 'Reclamation';
    $mailer->Body = 'Votre reclamation de type ' .$reclamation->getType().' a été peise en considération. merci pour votre retour/n Cordialement, équipe de service client';
    
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

            return $this->redirectToRoute('app_rec_index', [], Response::HTTP_SEE_OTHER);
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
    
        return $this->redirectToRoute('app_rec_indexclient');
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
    



#[Route('/stat2', name: 'app_rec_stat')]
public function listreclamation(Request $request, PaginatorInterface $paginator, ReclamationRepository $reclamationRepository): Response
{
    $reclamations = $reclamationRepository->findBy(['type' => 'service technique autre']);

    $stats = [
        'total' => count($reclamations),
        'types' => [],
    ];

    foreach ($reclamations as $reclamation) {
        $type = $reclamation->getType();
        if (!isset($stats['types'][$type])) {
            $stats['types'][$type] = 1;
        } else {
            $stats['types'][$type]++;
        }
    }

    $reclamations = $paginator->paginate(
        $reclamations,
        $request->query->getInt('page', 1),
        4
    );

    return $this->render('rec/index.html.twig', [
        'reclamations' => $reclamations,
        'stats' => $stats,
    ]);    
}



#[Route('/stat/{type}' , name:'stat')]

public function reclamationsParType(string $type, ReclamationRepository $repository)
{
    $type1Count = $repository->findReclamationsByType('Service');
    $type2Count = $repository->findReclamationsByType('Technique');
    $type3Count = $repository->findReclamationsByType('Autre');
    
    $chart = new PieChart();
    $chart->getData()->setArrayToDataTable([
        ['Type de réclamation', 'Nombre de réclamations'],
        ['Service', (int) $type1Count],
        ['Technique', (int) $type2Count],
        ['Autre', (int) $type3Count]
    ]);
    $chart->getOptions()->setTitle('Réclamations par type');
    
    return $this->render('rec/stat.html.twig', [
        'chart' => $chart
    ]);
}







#[Route('/stat' , name:'stat')]

    public function stat( ReclamationRepository $repository)
    {

        $newReclamationCount =$repository->findNewReclamation();
        $ReclamationTrite = $repository->findReclamationspartype();
       
        $EmnaChart1 = new PieChart();
        $EmnaChart1->getData()->setArrayToDataTable(
            [['Task', 'Hours per Day'],
                ['Reclamation Financier',((int) $ReclamationTrite)],
          
            ]
        );
        $EmnaChart1->getOptions()->setTitle("L'ETAT DES Reclamation D'AUJOURD'HUIT");
        $EmnaChart1->getOptions()->setHeight(400);
        $EmnaChart1->getOptions()->setIs3D(2);
        $EmnaChart1->getOptions()->setWidth(550);
        $EmnaChart1->getOptions()->getTitleTextStyle()->setBold(true);
        $EmnaChart1->getOptions()->getTitleTextStyle()->setColor('#009900');
        $EmnaChart1->getOptions()->getTitleTextStyle()->setItalic(true);
        $EmnaChart1->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $EmnaChart1->getOptions()->getTitleTextStyle()->setFontSize(15);

        return $this->render('rec/stat.html.twig', array(
            'EmnaChart1' => $EmnaChart1 ,
          ));




    }


}
