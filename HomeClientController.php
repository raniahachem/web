<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;



use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\Client;
use App\Repository\ConducteurRepository;
use App\Form\ClientType;

use Doctrine\Persistence\ManagerRegistry;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;

use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Validator\Constraints\Email;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Validator\Constraints\Regex;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;

use Symfony\Component\Form\Extension\Core\Type\PasswordType;


class HomeClientController extends AbstractController
{
    #[Route('/homeClient', name: 'app_home_client')]
    public function index(): Response
    {
        return $this->render('home_client/homeClient.html.twig', [
            'controller_name' => 'HomeClientController',
        ]);
    }
 /* #[Route('/newclient', name: 'app_client_new')]
public function new(Request $request): Response
{
    $client = new Client();
    $form = $this->createForm(ClientType::class, $client);

    $form->handleRequest($request);
    if ($form->isSubmitted() && $form->isValid()) {
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($client);
        $entityManager->flush();

        return $this->redirectToRoute('app_client_new');
    }

    return $this->render('home_client/index.html.twig', [
        'form' => $form->createView(),
    ]);
}*/
#[Route('/newclient', name: 'app_client_new')]
public function new(Request $request): Response
{
    $client = new Client();

    $towns = ['Tunis', 'Sfax', 'Sousse', 'Gabès', 'Nabeul', 'Bizerte', 'Kairouan', 'Gafsa', 'Monastir', 'Ariana', 'Mahdia', 'Sidi Bouzid', 'Ben Arous', 'Medenine', 'Manouba', 'Kasserine', 'Zaghouan', 'Kebili', 'Siliana', 'Tataouine', 'Kef', 'Jendouba', 'Tozeur'];
    $form = $this->createFormBuilder($client)
        ->add('cin_client', IntegerType::class)
        ->add('nom_client', TextType::class)
        ->add('prenom_client', TextType::class)
        ->add('telephone_client', IntegerType::class)
        ->add('email_client', EmailType::class)
        ->add('ville_client', ChoiceType::class, [
            'choices' => array_combine($towns, $towns),
            'attr' => [
                'class' => 'form-control',
                'id' => 'ville_client'
            ]
        ])
        ->add('mdp_client', PasswordType::class)
     
        ->getForm();
      

    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
   

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($client);
        $entityManager->flush();

        return $this->redirectToRoute('app_client_new');
    }

    return $this->render('home_client/index.html.twig', [
        'form' => $form->createView(),
    ]);
   
}






/*#[Route('/newclient', name: 'app_client_new')]
public function new(Request $request): Response
{
    $client = new Client();
    $form = $this->createFormBuilder($client)
        ->add('cin_client', IntegerType::class, [
            'required' => true,
            'constraints' => [
                new Length(['min' => 8, 'max' => 8]),
            ],
        ])
        ->add('nom_client', TextType::class, [
            'required' => true,
            'constraints' => [
                new NotBlank(),
            ],
        ])
        ->add('prenom_client', TextType::class, [
            'required' => true,
            'constraints' => [
                new NotBlank(),
            ],
        ])
        ->add('telephone_client', IntegerType::class, [
            'required' => true,
            'constraints' => [
                new Length(['min' => 8, 'max' => 8]),
            ],
        ])
        ->add('email_client', EmailType::class, [
            'required' => true,
            'constraints' => [
                new Email(),
                new Regex([
                    'pattern' => '/@/',
                    'message' => 'L\'email doit contenir un \'@\''
                ]),
            ],
        ])
        ->add('ville_client', TextType::class, [
            'required' => true,
            'constraints' => [
                new NotBlank(),
            ],
        ])
        ->add('mdp_client', PasswordType::class, [
            'required' => true,
            'constraints' => [
                new NotBlank(),
            ],
        ])
        ->getForm();

    $form->handleRequest($request);
    if ($form->isSubmitted() && $form->isValid()) {
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($client);
        $entityManager->flush();

        return $this->redirectToRoute('app_client_new');
    }

    return $this->render('home_client/index.html.twig', [
        'form' => $form->createView(),
    ]);
}*/

}



