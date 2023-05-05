<?php

namespace App\Controller;


use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use App\Form\LoginFormType;
use App\Entity\Commentaire;
use App\Entity\Conducteur;
use App\Entity\Client;
use App\Repository\ClientRepository;
use App\Entity\Avis;
use App\Repository\AvisRepository;

use Symfony\Component\HttpFoundation\RedirectResponse;

use App\Repository\ConducteurRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;

use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;


class ConducteurController extends AbstractController
{
    #[Route('/homeconducteur', name: 'app_conducteur')]
    public function ShowConducteur(ManagerRegistry $doctrine, $id_conducteur = null): Response
    { 
        $rep = $doctrine->getRepository(Client::class);
        $repo = $doctrine->getRepository(Conducteur::class);
        $repos = $doctrine->getRepository(Commentaire::class);
        $avisRepo = $doctrine->getRepository(Avis::class);
    
        $clients = $rep->findAll();
        $conducteurs = $repo->findAll();
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
            $conducteur = $repo->find($avi->getIdConducteur());
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
    
        return $this->render('conducteur/avisconducteur.html.twig', [
            'conducteurData' => $conducteurs,
            'client' => $clients,
            'avisData' => $avisData,
            'commentaireData' => $commentaireData,
        ]);
    }
    

    #[Route('/conducteurcommentaire', name: 'commentaire_conducteur')]
    public function Showcommentaire(ManagerRegistry $doctrine): Response
    { 
        $repo = $doctrine->getRepository(conducteur::class);
     
        $conducteur = $repo->findAll();

        return $this->render('conducteur/commentaireconducteur.html.twig', [
            'conducteur' => $conducteur,
       
        ]);
    }



    #[Route('/loginconducteur', name: 'conducteur_login')]
    public function login(AuthenticationUtils $authenticationUtils, Request $request): Response
    {
        // get the login error if there is one
        $error = $authenticationUtils->getLastAuthenticationError();
    
        // last username entered by the user
        $lastUsername = $authenticationUtils->getLastUsername();
    
        if ($request->getMethod() == 'POST') {
            // retrieve email and password from form data
            $email = $request->request->get('_email');
            $password = $request->request->get('_password');
            
            // query the database to find the conductor with the given email and password
            $conducteur = $this->getDoctrine()->getRepository(Conducteur::class)->findOneBy([
                'email_conducteur' => $email,
            ]);
            if (!$conducteur || !password_verify($password, $conducteur->getMdpConducteur())) {
                // display an error message if the email or password is incorrect
                $this->addFlash('error', 'Invalid email or password.');
            } else {
                // store the conductor ID in the session variable
                $this->get('session')->set('id_conducteur', $conducteur->getIdConducteur());
                // redirect to the homepage
                return new RedirectResponse($this->generateUrl('app_client'));
            }
        }
    
        return $this->render('login/loginconducteur.html.twig', [
            'last_username' => $lastUsername,
            'error' => $error,
        ]);
    }







}
