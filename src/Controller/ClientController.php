<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Request;
use DateTime;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use App\Entity\Client;
use App\Repository\ClientRepository;
use App\Entity\Avis;
use App\Repository\AvisRepository;
use App\Entity\Commentaire;
use App\Entity\Conducteur;
use Knp\Component\Pager\PaginatorInterface;
use App\Repository\ConducteurRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use App\Service\BadWordFilter;
use App\Service\CommentMailer;

class ClientController extends AbstractController
{
  
    #[Route('/homeclient', name: 'app_client')]
    public function homeclient(PaginatorInterface $paginator, Request $request, ManagerRegistry $doctrine, $id_conducteur = null): Response
    { 
        $rep = $doctrine->getRepository(Client::class);
        $repos = $doctrine->getRepository(Commentaire::class);
        $avisRepo = $doctrine->getRepository(Avis::class);
        $conducteurRepository = $doctrine->getRepository(Conducteur::class);
    
        $clients = $rep->findAll();
        $conducteurs = $conducteurRepository->findAll();
        $commentaires = $repos->findAll();
        $avis = $avisRepo->findAll();
    
        $commentaireData = [];
        $avisData = [];
    
        foreach ($commentaires as $commentaire) {
            $client = $rep->find($commentaire->getIdClient());
            $commentaireData[] = [
                'client' => $client,
                'commentaire' => $commentaire,
            ];
        }
    
        foreach ($avis as $avi) {
            $client = $rep->find($avi->getIdClient());
            $conducteur = $conducteurRepository->find($avi->getIdConducteur());
            $avisData[] = [
                'client' => $client,
                'conducteur' => $conducteur,
                'avis' => $avi,
            ];
        }
    
        if ($id_conducteur) {
            $avisData = array_filter($avisData, function ($item) use ($id_conducteur) {
                return $item['conducteur']->getIdConducteur() == $id_conducteur;
            });
        }
    
         // Retrieve the entity manager of Doctrine
      $em = $this->getDoctrine()->getManager();
      
      // Get some repository of data, in our case we have an Appointments entity
      $ConducteurRepository = $em->getRepository(Conducteur::class);
              
      // Find all the data on the Appointments table, filter your query as you need
      $allConducteursQuery = $ConducteurRepository->createQueryBuilder('c')
          ->where('c.nom_conducteur != :nom_conducteur')
          ->setParameter('nom_conducteur', 'canceled')
          ->getQuery();
      
      // Paginate the results of the query
      $conducteurp = $paginator->paginate(
          // Doctrine Query, not results
          $allConducteursQuery,
          // Define the page parameter
          $request->query->getInt('page', 1),
          // Items per page
          1
      );
      
    
        return $this->render('client/index.html.twig', [
            'conducteurData' => $conducteurs,
            'client' => $clients,
            'avisData' => $avisData,
            'conducteurData' => $conducteurp,  
            'commentaireData' => $commentaireData,
        ]);
    }
    






    #[Route('/addcommentc', name: 'add_commentc')]
    public function addcomment(Request $request, ManagerRegistry $doctrine, BadWordFilter $badWordFilter, CommentMailer $commentMailer)
    {    
        $entityManager = $this->getDoctrine()->getManager();
        $repo = $doctrine->getRepository(conducteur::class);
        
        // Get the values from the form
        $id_client = $request->request->get('id_client');
        $id_conducteur = $request->request->get('id_conducteur');
        $contenu = $request->request->get('contenu');
        $id_avis = $request->request->get('id_avis');
        $contenu = trim($request->request->get('contenu'));
    
        // Check if the "contenu" field is empty
    
        if (empty($contenu)) {
            // Show an alert message
            $response = new Response('<script>alert("Le contenu est vide!"); window.location.href = "'. $this->generateUrl('app_client') .'";</script>');
            return $response;
    
        }
        
        // Filter bad words from the comment
        $filteredContenu = $badWordFilter->filter($contenu);
    
        // Set the filtered comment content
    
    
    
        // Find the conducteur object
        $conducteur = $repo->find($id_conducteur);
    
        // Create a new Avis object and set its properties
        $comment = new commentaire();
        $comment->setIdClient('13');
        $comment->setIdConducteur($id_conducteur);
        $comment->setContenu($contenu);
        $comment->setContenu($filteredContenu);
        $comment->setDate(new DateTime());
        $comment->setIdAvis(13);
    
    
        // Save the Avis object to the database
        $entityManager->persist($comment);
        $entityManager->flush();
    


 // Send comment notification email
 $commentMailer->sendCommentNotification($comment);

 // Redirect or render the view as needed

       // Get all comments for the conducteur
       $commentaireRepository = $entityManager->getRepository(Commentaire::class);
       $commentaire = $commentaireRepository->findBy(['id_conducteur' => $id_conducteur]);
      $commentaires = $commentaireRepository->findAll();
      
    
       // Send an email to the conducteur
       return $this->redirectToRoute('app_client');
       // Redirect the user back to the conducteur page
       return $this->render('client/index.html.twig', [
           'conducteur' => $conducteur,
           'commentaires' => $commentaire,
       'comment' =>   $comment,
        ]);
    }




#[Route('/commentaireclient/delete/{id}', name: 'client_commentaire_delete')]
    public function deleteCommentaireclient($id, ManagerRegistry $doctrine): Response
    {
        $entityManager = $doctrine->getManager();
        $commentaire = $entityManager->getRepository(commentaire::class)->find($id);
    
        if (!$commentaire) {
            throw $this->createNotFoundException('Commentaire non trouvé');
        }
    
        $entityManager->remove($commentaire);
        $entityManager->flush();
    
        return $this->redirectToRoute('app_client');
    }

    #[Route('/commentaireclientupdate/{id}', name: 'client_commentaire_update')]
    public function updateCommentaireclient(Request $request, Commentaire $commentaire): Response
    {
        $commentaireData = [];
        $avisData = [];
        $conducteurs= [];
        $clients= [];
        $form = $this->createFormBuilder($commentaire)
            ->add('contenu', TextType::class, [
                'data' => $commentaire->getContenu(),
            ])
            ->getForm();
    
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            $commentaire->setDate(new \DateTime());
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($commentaire);
            $entityManager->flush();
    
            return $this->redirectToRoute('app_client');
        }
    
        return $this->render('client/edit.html.twig', [
            'conducteurData' => $conducteurs,
            'client' => $clients,
            'avisData' => $avisData,
            'commentaireData' => $commentaireData,
            'form' => $form->createView(),
        ]);
    }
    
}
