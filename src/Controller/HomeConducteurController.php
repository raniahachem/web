<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Doctrine\ORM\EntityManagerInterface;
use App\Entity\Conducteur;
use App\Repository\ConducteurRepository;
use App\Form\ConducteurType;
use Symfony\Component\String\Slugger\SluggerInterface;

use Doctrine\Persistence\ManagerRegistry;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Security\Core\Security;

use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Validator\Constraints\Email;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;


class HomeConducteurController extends AbstractController
{
    #[Route('/', name: 'app_home_conducteur')]
    public function index(): Response
    {
        $user = $this->getUser();
        $username = $user ? $user->getUserIdentifier() : '';
        return $this->render('home_conducteur/homeConducteur.html.twig', [
            'controller_name' => 'HomeConducteurController',
            'username' => $username,
        ]);    
     
    }





    #[Route('/{id_conducteur}/edit', name: 'app_conducteur_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Conducteur $conducteur, ConducteurRepository $conducteurRepository): Response
    {
        $form = $this->createForm(ConducteurType::class, $conducteur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $conducteurRepository->save($conducteur, true);

            return $this->redirectToRoute('app_conducteur_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('conducteur/homeConducteur.html.twig', [
            'conducteur' => $conducteur,
            'form' => $form,
        ]);
    }
    #[Route('/new', name: 'app_conducteur_new')]
public function new(Request $request, SluggerInterface $slugger): Response
{
    $conducteur = new Conducteur();
    $license = ['A', 'B'];

    $towns = ['Tunis', 'Sfax', 'Sousse', 'Gabès', 'Nabeul', 'Bizerte', 'Kairouan', 'Gafsa', 'Monastir', 'Ariana', 'Mahdia', 'Sidi Bouzid', 'Ben Arous', 'Medenine', 'Manouba', 'Kasserine', 'Zaghouan', 'Kebili', 'Siliana', 'Tataouine', 'Kef', 'Jendouba', 'Tozeur'];
    $form = $this->createFormBuilder($conducteur)
        ->add('cin_conducteur', IntegerType::class)
        ->add('nom_conducteur', TextType::class)
        ->add('prenom_conducteur', TextType::class)
        ->add('telephone_conducteur', IntegerType::class)
        ->add('email_conducteur', EmailType::class)
        ->add('ville_conducteur', ChoiceType::class, [
            'choices' => array_combine($towns, $towns),
            'attr' => [
                'class' => 'form-control',
                'id' => 'ville_conducteur'
            ]
        ])
        ->add('mdp_conducteur', PasswordType::class)
        ->add('type_de_permis', ChoiceType::class,[
            'choices' => array_combine($license, $license),
            'attr' => [
                'class' => 'form-control',
                'id' => 'type_de_permis'
            ]
        ])
        ->add('image_conducteur', FileType::class, [
            'label' => 'Image',
            'required' => false,
            'attr' => [
                'accept' => '',
                'class' => 'app_update'
            ]
        ])
        ->getForm();

    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        $imageFile = $form->get('image_conducteur')->getData();

        if ($imageFile) {
            $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
            // this is needed to safely include the file name as part of the URL
            $safeFilename = $slugger->slug($originalFilename);
            $newFilename = $safeFilename.'-'.uniqid().'.'.$imageFile->guessExtension();
        
            // Move the file to the directory where images are stored
            try {
                $imageFile->move(
                    $this->getParameter('images_directory'),
                    $newFilename
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }

            $conducteur->setImageConducteur('/img/' . $newFilename);
        }

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($conducteur);
        $entityManager->flush();

        return $this->redirectToRoute('app_conducteur_new');
    }

    return $this->render('home_conducteur/index.html.twig', [
        'form' => $form->createView(),
    ]);
}

 
    

}
