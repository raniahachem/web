<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\Client;
use App\Repository\ConducteurRepository;

use Doctrine\Persistence\ManagerRegistry;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\ClientType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;

class ClientController extends AbstractController
{
    #[Route('/client', name: 'app_client')]
    public function ShowClient(ManagerRegistry $doctrine): Response
    { 
        $repo = $doctrine->getRepository(client::class);
     
        $client = $repo->findAll();

        return $this->render('client/client.html.twig', [
            'client' => $client,
       
        ]);
    }
    #[Route('/client/delete/{id}', name: 'app_client_delete')]
    public function deleteClient($id, ManagerRegistry $doctrine): Response
    {
        $entityManager = $doctrine->getManager();
        $client = $entityManager->getRepository(client::class)->find($id);
    
        if (!$client) {
            throw $this->createNotFoundException('client non trouvé');
        }
    
        $entityManager->remove($client);
        $entityManager->flush();
    
        return $this->redirectToRoute('app_client');
    }



    #[Route('/clientcompte', name: 'compte_client_edit')]
    public function CompteClient(ManagerRegistry $doctrine): Response
    { 
        $repo = $doctrine->getRepository(client::class);
     
        $client = $repo->findAll();

        return $this->render('home_client/profile.html.twig', [
            'client' => $client,
       
        ]);
    }





    #[Route('/updateclient/{id}', name: 'app_client_edit')]
    public function updateClient(Request $request, ManagerRegistry $doctrine, int $id): Response
    {
        $client = $doctrine->getRepository(Client::class)->find($id);
    
        $form = $this->createFormBuilder($client)
            ->add('nom_client', TextType::class)
            ->add('prenom_client', TextType::class)
            ->add('cin_client', IntegerType::class)
            ->add('ville_client', TextType::class)
            ->add('telephone_client', IntegerType::class)
            ->add('email_client', EmailType::class)
            ->add('mdp_client', PasswordType::class)
            ->add('save', SubmitType::class, ['label' => 'Update Client'])
            ->getForm();
    
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            $doctrine->getManager()->flush();
    
            // Render the table row with the updated client data
            return $this->redirectToRoute('compte_client_edit');
    
            return new Response($html);
        }
    
        // Render the update form HTML
        $html = $this->renderView('client/index.html.twig', [
            'form' => $form->createView(),
            'client' => $client,
        ]);
    
        return new Response($html);
    }
    
}




