<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Request;
use App\Repository\AvisRepository;
use App\Entity\Avis;
use App\Entity\Commentaire;
use App\Entity\Conducteur;
use App\Repository\ConducteurRepository;

use Doctrine\Persistence\ManagerRegistry;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;

use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;


class HomeController extends AbstractController
{
    #[Route('/home', name: 'app_home')]
    public function index(): Response
    {
        return $this->render('home/index.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }


    #[Route('/conducteurs', name: 'conducteur_avis')]
    public function ShowConducteur(ManagerRegistry $doctrine): Response
    { 
        $repo = $doctrine->getRepository(conducteur::class);
     
        $conducteur = $repo->findAll();

        return $this->render('home/index.html.twig', [
            'conducteur' => $conducteur,
       
        ]);
    }
    #[Route('/addavisconducteur', name: 'add_avis_conducteur')]
public function addavis(Request $request, ManagerRegistry $doctrine)
{    
    $entityManager = $this->getDoctrine()->getManager();
    $repo = $doctrine->getRepository(conducteur::class);
    
    // Get the values from the form
    $id_client = $request->request->get('id_client');
    $id_conducteur = $request->request->get('id_conducteur');
    $rating = $request->request->get('rating');
    
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

    // Redirect the user back to the conducteur page
    return $this->render('home/index.html.twig', [
        'conducteur' => $conducteur,
   
    ]);
}





}
