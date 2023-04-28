<?php

namespace App\Controller;

use App\Entity\Offre;
use App\Entity\Reservation;
use App\Service\MailerService;
use App\Form\OffreType;
use App\Form\SearchType;
use App\Repository\OffreRepository;
use App\Repository\ReservationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Mime\Email;
 
use Symfony\Component\Mailer\MailerInterface;

use Symfony\Component\HttpFoundation\JsonResponse;
 

use Knp\Component\Pager\PaginatorInterface;
use PDO;
 
use Knp\Bundle\PaginatorBundle\Pagination\SlidingPaginationInterface;
use Knp\Bundle\PaginatorBundle\Twig\Extension\PaginationExtension;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
#[Route('/offre')]
class OffreController extends AbstractController
{
    #[Route('/', name: 'app_offre_index', methods: ['GET'])]
    public function indexAdminConducteur(OffreRepository $offreRepository,Request $request, PaginatorInterface $paginator): Response
    {

        $offres = $offreRepository->findAll();
        $offres = $paginator->paginate(
            $offres,
            $request->query->getInt('page', 1),
            7
        );

        return $this->render('offre/index.html.twig', [
            'offres' => $offres,
        ]);

         
    }
    #[Route('/admin', name: 'app_offre_index_admin', methods: ['GET'])]
    public function indexAdmin(OffreRepository $offreRepository,Request $request, PaginatorInterface $paginator): Response
    {

        $offres = $offreRepository->findAll();
        $offres = $paginator->paginate(
            $offres,
            $request->query->getInt('page', 1),
            7
        );

        return $this->render('offre/indexAdmin.html.twig', [
            'offres' => $offres,
        ]);
 
    }
    #[Route('/listOffre', name: 'app_offre_index_Client', methods: ['GET'])]
    public function indexClient(OffreRepository $offreRepository,ReservationRepository $reservationRepository,Request $request, PaginatorInterface $paginator): Response
    {
         

    $offres = $offreRepository->findAll();
     
     
    return $this->render('offre/indexClient.html.twig', [
        'offres' => $offres,
         

    ]);
 
     
    }

     
    #[Route('/stat', name: 'app_offre_stat', methods: ['GET', 'POST'])]
    public function stat(Request $request, OffreRepository $offreRepository): Response
    {
        return $this->render('offre/stats.html.twig', [
            'offres' => $offreRepository->findAll(),
        ]);
    }

    #[Route('/statpublic', name: 'app_offre_statpublic', methods: ['GET', 'POST'])]
    public function statpublic(Request $request, OffreRepository $offreRepository): Response
    {
        return $this->render('public/stat.php', [
            'offres' => $offreRepository->findAll(),
        ]);
    }


 
    
    #[Route('/new', name: 'app_offre_new', methods: ['GET', 'POST'])]
    public function new(Request $request, OffreRepository $offreRepository,MailerInterface $mailer): Response
    {
         
             
        $offre = new Offre();
        
        $form = $this->createForm(OffreType::class, $offre);
        $form->handleRequest($request);

         

        if ($form->isSubmitted() && $form->isValid()) {

            $offreRepository->save($offre,$mailer, true);
 

            
            return $this->redirectToRoute('app_offre_index', [], Response::HTTP_SEE_OTHER);
        }
        return $this->renderForm('offre/new.html.twig', [
            'offre' => $offre,
            'form' => $form,
        ]);
    }
    
    #[Route('front/new', name: 'app_offre_new_front', methods: ['GET', 'POST'])]
    public function newfront(Request $request, OffreRepository $offreRepository): Response
    {
         
             
        $offre = new Offre();
        
        $form = $this->createForm(OffreType::class, $offre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $offreRepository->save($offre, true);
            return $this->redirectToRoute('app_offre_index_Client', [], Response::HTTP_SEE_OTHER);
        }
        return $this->renderForm('offre/newfront.html.twig', [
            'offre' => $offre,
            'form' => $form,
        ]);
    }

    #[Route('reserver/new', name: 'app_offre_new_reserver', methods: ['GET', 'POST'])]
    public function newreserver(Request $request, OffreRepository $offreRepository): Response
    {
         
             
        $offre = new Offre();
        
        $form = $this->createForm(OffreType::class, $offre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $offreRepository->save($offre, true);
            return $this->redirectToRoute('app_offre_index_Client', [], Response::HTTP_SEE_OTHER);
        }
        return $this->renderForm('offre/newadmin.html.twig', [
            'offre' => $offre,
            'form' => $form,
        ]);
    }

    #[Route('/{idOffre}', name: 'app_offre_show', methods: ['GET'])]
    public function show(Offre $offre): Response
    {
        return $this->render('offre/show.html.twig', [
            'offre' => $offre,
        ]);
    }
    #[Route('/front/{idOffre}', name: 'app_offre_show_client', methods: ['GET'])]
    public function showfront(Offre $offre): Response
    {
      
        return $this->render('offre/showfront.html.twig', [
            'offre' => $offre,
        ]);
    }
    #[Route('/admin/{idOffre}', name: 'app_offre_show_admin', methods: ['GET'])]
    public function showAdmin(Offre $offre): Response
    {
      
        return $this->render('offre/showAdmin.html.twig', [
            'offre' => $offre,
        ]);
    }
     
    

    #[Route('/{idOffre}/edit', name: 'app_offre_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Offre $offre, OffreRepository $offreRepository, MailerService $mailer
    ): Response
    {
        $form = $this->createForm(OffreType::class, $offre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $offreRepository->save($offre, true);
            $mailMessage = $offre->getIdOffre();
            $this->addFlash('succes', $offre->getIdOffre());
            $mailer->sendEmail(content: $mailMessage);


            return $this->redirectToRoute('app_offre_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('offre/edit.html.twig', [
            'offre' => $offre,
            'form' => $form,
        ]);
    }

    #[Route('/editfront/{idOffre}', name: 'app_offre_edit_front', methods: ['GET', 'POST'])]
    public function editfront(Request $request, Offre $offre, OffreRepository $offreRepository, MailerService $mailer
    ): Response
    {
        $form = $this->createForm(OffreType::class, $offre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $offreRepository->save($offre, true);
            $mailMessage = $offre->getIdOffre();
            $this->addFlash('succes', $offre->getIdOffre());
            $mailer->sendEmail(content: $mailMessage);


            return $this->redirectToRoute('app_offre_indexClient', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('offre/editfront.html.twig', [
            'offre' => $offre,
            'form' => $form,
        ]);
    }


    //#[Route('/b', name: 'app_offre_indexb', methods: ['GET'])]
    //public function index(OffreRepository $offreRepository): Response
    //{
        //return $this->render('offre/index.html.twig', [
            //'offres' => $offreRepository->findAll(),
        //]);
    //}

    #[Route('/{idOffre}', name: 'app_offre_delete', methods: ['POST'])]
    public function delete(Request $request, Offre $offre, OffreRepository $offreRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$offre->getIdOffre(), $request->request->get('_token'))) {
            $offreRepository->remove($offre, true);
        }

        return $this->redirectToRoute('app_offre_index', [], Response::HTTP_SEE_OTHER);
    }

      
    #[Route('/list/filter', name: 'app_film_list_search')]
public function searchList(Request $request, OffreRepository $offreRepository): JsonResponse
{
    $searchTerm = $request->query->get('searchTerm');
  
    $offres = $offreRepository->findLikeNom($searchTerm);
   $form=$this->createForm(SearchType::class);
   $form->handleRequest($request);
    if ($form->isSubmitted() && $form->isValid()) {
    $query = $form->getData()['query'];
    dd($query);
    // Perform the search with the query here...
}

    $data = [];
    foreach ($offres as $offre) {
        $data[] = [
            'idOffre' => $offre->getIdOffre(),
            'idClient' => $offre->getIdClient(),
            'destination' => $offre->getDestination(),
            'ptDepart' => $offre->getPtDepart(),
            'prix' => $offre->getPrix(),
            'typevehicule' => $offre->getTypeVehicule(),
        ];
    }

    return new JsonResponse($data);
}

 


    #[Route('/ansewrs/popular', name: 'app_popular_ansewrs')]
    public function popularAnsewrs(OffreRepository $offreRepository, Request $request)
    {
        $offres = $offreRepository->findMostPopular(
            $request->query->get('q')
        );

        return $this->render('offre/index.html.twig', [
            'offres' => $offres,
        ]);
    }


    /*#[Route('/searchRec', name: 'searchRec')]
    public function searchRec(Request $request, OffreRepository $repository)
    {
        $requestString = $request->get('q');
        $Offres = $repository->findbytype($requestString);
    
        return $this->render('offre/index.html.twig', [
            'offres' => $Offres,
        ]);
    }*/

     
    #[Route('offre/typeVehiculecl/{typeVehicule}', name: 'typeVehiculeClient', methods: ['GET'])]
    public function indextypeVehiculeC(OffreRepository $offreRepository, $typeVehicule): Response
    {
        return $this->render('offre/indexClient2.html.twig', [
            'offres' => $offreRepository->findByTypeVehicule($typeVehicule),
        ]);
    }

    #[Route('/idconducteur/{idConducteur}', name: 'app_reservation_index_Clientid', methods: ['GET'])]
    public function indexidConducteur(OffreRepository $offreRepository, $idConducteur): Response
    {
        return $this->render('offre/index.html.twig', [
            'offres' => $reservationRepository->findByIdConducteur($idConducteur),
        ]);
    }

}


    /*#[Route('/chart', name: 'chart')]
    public function charteOffres(): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        // Se connecter à la base de données
        $pdo = new PDO('mysql:host=localhost;dbname=autoxpress', 'root', '');
    
        // Récupérer les données des types de véhicules et leurs quantités correspondantes depuis la base de données
        $stmt = $pdo->query('SELECT Type_vehicule, COUNT(*) AS quantite FROM Offre GROUP BY Type_vehicule');
        $vehicules = $stmt->fetchAll(PDO::FETCH_ASSOC);
    
        // Créer un tableau des noms de types de véhicules et des quantités correspondantes
        $labels = array_column($vehicules, 'Type_vehicule');
        $data = array_column($vehicules, 'quantite');
    
        // Renvoyer la vue avec les données
        return $this->render('offre/chart.html.twig', [
            'labels' => $labels,
            'data' => $data,
        ]);
    }*/

    /*#[Route('/stats', name: 'stats')]
    public function stats() 
    {
        // Se connecter à la base de données
        $pdo = new PDO('mysql:host=localhost;dbname=autoxpress', 'root', '');
        // Récupérer les données des types de véhicules et leurs quantités correspondantes depuis la base de données
        $stmt = $pdo->query('SELECT Type_vehicule, COUNT(*) AS quantite FROM Offre GROUP BY Type_vehicule');
        $vehicules = $stmt->fetchAll(PDO::FETCH_ASSOC);

        // Créer un tableau des noms de types de véhicules et des quantités correspondantes
        $labels = array_column($vehicules, 'Type_vehicule');
        $data = array_column($vehicules, 'quantite');

        // Afficher la vue avec les données de statistiques
        return $this->render('stats.html.twig', [
            'labels' => $labels,
            'data' => $data,
        ]);
    }*/
 
      
    
    











    
       
    
     
     