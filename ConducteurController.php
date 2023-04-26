<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\Conducteur;
use App\Repository\ConducteurRepository;

use Doctrine\Persistence\ManagerRegistry;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Security\Core\Security;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\FileType;

class ConducteurController extends AbstractController
{
    #[Route('/conducteur', name: 'app_conducteur')]
    public function ShowConducteur(ManagerRegistry $doctrine): Response
    { 
        $repo = $doctrine->getRepository(conducteur::class);
     
        $conducteur = $repo->findAll();

        return $this->render('conducteur/conducteur.html.twig', [
            'conducteur' => $conducteur,
       
        ]);
    }
    #[Route('/conducteurcompte', name: 'compte_conducteur')]
    public function CompteConducteur(ManagerRegistry $doctrine): Response
    { 
        $user = $this->getUser();
        if (!$user) {
            // User is not authenticated, redirect to login page
            return $this->redirectToRoute('app_login');
        }
        $username = $user->getUserIdentifier();
        $repo = $doctrine->getRepository(Conducteur::class);
        $conducteur = $repo->findOneBy(['email_conducteur' => $username]);
    
        return $this->render('home_conducteur/profile.html.twig', [
            'conducteur' => $conducteur,
            'username'=>$username,
        ]);
    }
    
    


    #[Route('/conducteur/delete/{id}', name: 'app_conducteur_delete')]
    public function deleteConducteur($id, ManagerRegistry $doctrine): Response
    {
        $entityManager = $doctrine->getManager();
        $conducteur = $entityManager->getRepository(conducteur::class)->find($id);
    
        if (!$conducteur) {
            throw $this->createNotFoundException('conducteur non trouvé');
        }
    
        $entityManager->remove($conducteur);
        $entityManager->flush();
    
        return $this->redirectToRoute('app_conducteur');
    }





    #[Route('/updatecompteconducteur/{id}', name: 'compte_conducteur_edit')]
    public function updateCompteConducteur(Request $request, ManagerRegistry $doctrine, int $id): Response
    {
        $conducteur = $doctrine->getRepository(Conducteur::class)->find($id);
    
        $form = $this->createFormBuilder($conducteur)
            ->add('cin_conducteur', IntegerType::class)
            ->add('nom_conducteur', TextType::class)
            ->add('prenom_conducteur', TextType::class)
            ->add('telephone_conducteur', IntegerType::class)
            ->add('email_conducteur', EmailType::class)
            ->add('ville_conducteur', TextType::class)
            ->add('mdp_conducteur', PasswordType::class)
            ->add('type_de_permis', TextType::class)
            ->add('image_conducteur', TextType::class)
            ->add('save', SubmitType::class, ['label' => 'Done'])
            ->getForm();
    
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            $doctrine->getManager()->flush();
    
            // Render the table row with the updated conducteur data
            return $this->redirectToRoute('compte_conducteur');
    
            return new Response($html);
        }
    
        // Render the update form HTML
        $html = $this->renderView('conducteur/index.html.twig', [
            'form' => $form->createView(),
            'conducteur' => $conducteur,
        ]);
    
        return new Response($html);
    }




    #[Route('/updateconducteur/{id}', name: 'conducteur_edit')]
    public function updateConducteur(Request $request, ManagerRegistry $doctrine, int $id): Response
    {
        $conducteur = $doctrine->getRepository(Conducteur::class)->find($id);
    
        $form = $this->createFormBuilder($conducteur)
            ->add('cin_conducteur', IntegerType::class)
            ->add('nom_conducteur', TextType::class)
            ->add('prenom_conducteur', TextType::class)
            ->add('telephone_conducteur', IntegerType::class)
            ->add('email_conducteur', EmailType::class)
            ->add('ville_conducteur', TextType::class)
            ->add('mdp_conducteur', TextType::class)
            ->add('type_de_permis', TextType::class)
            ->add('image_conducteur', TextType::class)
            ->add('save', SubmitType::class, ['label' => 'Update Conducteur'])
            ->getForm();
    
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            $doctrine->getManager()->flush();
    
            // Render the table row with the updated conducteur data
            return $this->redirectToRoute('app_conducteur');
    
            return new Response($html);
        }
    
        // Render the update form HTML
        $html = $this->renderView('conducteur/index.html.twig', [
            'form' => $form->createView(),
            'conducteur' => $conducteur,
        ]);
    
        return new Response($html);
    }
    
}




