<?php

namespace App\Controller;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use App\Repository\CommentaireRepository;
use App\Entity\Commentaire;
use App\Repository\AvisRepository;
use App\Entity\Conducteur;
use App\Repository\ConducteurRepository;
use App\Entity\Avis;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use DateTime;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;


class CommentaireController extends AbstractController
{
    #[Route('/commentaire', name: 'app_commentaire')]
    public function ShowCommentaire(ManagerRegistry $doctrine): Response
    { 
        $repo = $doctrine->getRepository(commentaire::class);
     
        $commentaire = $repo->findAll();

        return $this->render('commentaire/commentaire.html.twig', [
            'commentaire' => $commentaire,
       
        ]);
    }

 
    
    #[Route('/showcommentaire', name: 'show_commentaire')]
    public function ShowComments(ManagerRegistry $doctrine): Response
    { 
        $repo = $doctrine->getRepository(commentaire::class);
     
        $commentaire = $repo->findAll();
        return $this->redirectToRoute('app_conducteur_avis');

        return $this->render('conducteur/avisconducteur.html.twig', [
            'commentaires' => $commentaire,
       
        ]);
    }

    #[Route('/commentaire/delete/{id}', name: 'app_commentaire_delete')]
    public function deleteCommentaire($id, ManagerRegistry $doctrine): Response
    {
        $entityManager = $doctrine->getManager();
        $commentaire = $entityManager->getRepository(commentaire::class)->find($id);
    
        if (!$commentaire) {
            throw $this->createNotFoundException('Commentaire non trouvé');
        }
    
        $entityManager->remove($commentaire);
        $entityManager->flush();
    
        return $this->redirectToRoute('app_commentaire');
    }


   



    
    #[Route('/avis/delete/{id}', name: 'app_avis_delete')]
    public function deleteAvis($id, ManagerRegistry $doctrine): Response
    {
        $entityManager = $doctrine->getManager();
        $avis = $entityManager->getRepository(avis::class)->find($id);
    
        if (!$avis) {
            throw $this->createNotFoundException('Avis non trouvé');
        }
    
        $entityManager->remove($avis);
        $entityManager->flush();
    
        return $this->redirectToRoute('app_avis');
    }


    #[Route('/addavis', name: 'add_avis')]
public function addavis(Request $request, ManagerRegistry $doctrine)
{    
    $entityManager = $this->getDoctrine()->getManager();
    $repo = $doctrine->getRepository(conducteur::class);

    // Get the values from the form
    $id_client = $request->request->get('id_client');
    $id_conducteur = $request->request->get('id_conducteur');
    $rating = $request->request->get('rating');
    $id = $request->request->get('id_avis');

    
    // Find the conducteur object
    $conducteur = $repo->find($id_conducteur);

    // Create a new Avis object and set its properties
    $avis = new Avis();
    $avis->setIdClient('13');
    $avis->setIdConducteur($id_conducteur);
    $avis->setRating($rating);



    // Save the Avis object to the database
    $entityManager->persist($avis);
    $entityManager->flush();
    return $this->redirectToRoute('app_client');

    // Redirect the user back to the conducteur page
    return $this->render('conducteur/avisconducteur.html.twig', [
        'conducteur' => $conducteur,
   
    ]);
}


#[Route('/addcomment', name: 'add_comment')]
public function addcomment(Request $request, ManagerRegistry $doctrine)
{    
    $entityManager = $this->getDoctrine()->getManager();
    $repo = $doctrine->getRepository(conducteur::class);
    
    // Get the values from the form
    $id_client = $request->request->get('id_client');
    $id_conducteur = $request->request->get('id_conducteur');
    $contenu = $request->request->get('contenu');
    $id_avis = $request->request->get('id_avis');
 // Get the value from the "contenu" field
$contenu = trim($request->request->get('contenu'));

// Check if the "contenu" field is empty
if ($contenu == "") {
  
    // Show an alert message
    $this->addFlash('error', 'Le contenu est vide!');
    return $this->redirectToRoute('app_conducteur');
}

    // Find the conducteur object
    $conducteur = $repo->find($id_conducteur);

    // Create a new Avis object and set its properties
    $comment = new commentaire();
    $comment->setIdClient('13');
    $comment->setIdConducteur($id_conducteur);
    $comment->setContenu($contenu);
    $comment->setDate(new DateTime());
    $comment->setIdAvis(13);


    // Save the Avis object to the database
    $entityManager->persist($comment);
    $entityManager->flush();

   // Get all comments for the conducteur
   $commentaireRepository = $entityManager->getRepository(Commentaire::class);
   $commentaire = $commentaireRepository->findBy(['id_conducteur' => $id_conducteur]);
   return $this->redirectToRoute('app_conducteur');
   // Redirect the user back to the conducteur page
   return $this->render('conducteur/avisconducteur.html.twig', [
       'conducteur' => $conducteur,
       'commentaires' => $commentaire,
   'comment' =>   $comment,
    ]);
}


    
    
}
