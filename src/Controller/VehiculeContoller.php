<?php

namespace App\Controller;

use Doctrine\Persistence\ManagerRegistry;

use App\Entity\Vehicule;
use App\Service\DompdfFactory;
use Dompdf\Dompdf;
use App\Form\VehiculeType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\DBAL\Exception\UniqueConstraintViolationException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use App\Entity\Conducteur;
use Knp\Snappy\Pdf;

use Knp\Bundle\SnappyBundle\Snappy\LoggableGenerator;

use Symfony\Component\HttpFoundation\Session\SessionInterface;






#[Route('/vehicule')]
class VehiculeContoller extends AbstractController

{

    private $dompdfFactory;

    public function __construct(DompdfFactory $dompdfFactory)
    {
        $this->dompdfFactory = $dompdfFactory;
    }


    #[Route('/{id}/pdf', name: 'app_vehicule_contoller_pdf', methods: ['GET'])]
    public function generatePdf(Vehicule $vehicule): Response
    {
        $html = $this->renderView('vehicule/pdf.html.twig', [
            'vehicule' => $vehicule,
        ]);

        $dompdf = $this->dompdfFactory->create();
        $dompdf->loadHtml($html);
        $dompdf->setPaper('A4', 'portrait');
        $dompdf->render();

        $response = new Response($dompdf->output());
        $response->headers->set('Content-Type', 'application/pdf');
        $response->headers->set('Content-Disposition', 'attachment; filename="' . $vehicule->getId() . '.pdf"');

        return $response;
    }



    #[Route('/bro', name: 'app_vehicule_contoller_index', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager, SessionInterface $session): Response
    {
        $vehicules = $entityManager
            ->getRepository(Vehicule::class)
            ->findAll();
        $conducteur_id = $session->get('conducteur_id');


        return $this->render('vehicule/index.html.twig', [
            'vehicules' => $vehicules,
            'conducteur_id' => $conducteur_id,
        ]);
    }



    #[Route('/new/{id_conducteur}', name: 'app_vehicule_contoller_new', methods: ['GET', 'POST'])]
    public function ajouterVehicule(int $id_conducteur, Request $request, EntityManagerInterface $entityManager): Response

    {
        $vehicule = new Vehicule();

        $marqueChoices = [
            'Voiture' => ['Audi', 'BMW', 'Chevrolet', 'Ferrari', 'Ford', 'Hyundai', 'Mercedes', 'Toyota', 'Volkswagen'],
            'Van' => ['Mercedes', 'Ford', 'Chevrolet', 'Dodge', 'Nissan', 'RAM', 'Toyota', 'Volkswagen', 'Fiat', 'GMC'],
            'Camion' => ['Ford', 'Chevrolet', 'RAM', 'GMC', 'Toyota', 'Nissan', 'Jeep', 'Dodge'],
            'Bus' => ['Blue Bird', 'Thomas Built Buses', 'Gillig', 'New Flyer', 'Prevost', 'Mci', 'Van Hool']
        ];

        $flatMarqueChoices = [];
        foreach ($marqueChoices as $type => $marques) {
            foreach ($marques as $marque) {
                $flatMarqueChoices[$marque] = $marque;
            }
        }

        $form = $this->createForm(VehiculeType::class, $vehicule, [
            'marque_choices' => $flatMarqueChoices,
        ]);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {


            /** @var UploadedFile $imageFile */
            $imageFile = $form->get('imagev')->getData();

            if ($imageFile) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = $vehicule->getId() . '-' . uniqid() . '.' . $imageFile->guessExtension();

                try {
                    $imageFile->move(
                        $this->getParameter('imagev_directory'),
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }



                $vehicule->setImagev($newFilename);
            }
            $conducteur = $entityManager->getRepository(Conducteur::class)->find($id_conducteur);
            if (!$conducteur) {
                throw $this->createNotFoundException('The conductor does not exist');
            }

            $vehicule->setIdconducteur($conducteur);

            // Add this line to check if the Conducteur is being set properly
            if ($vehicule->getIdconducteur() === null) {
                throw new \Exception('Failed to set Conducteur for Vehicule');
            }




            try {
                $entityManager->persist($vehicule);
                $entityManager->flush();

                return $this->redirectToRoute('app_vehicule_contoller_index', [], Response::HTTP_SEE_OTHER);
            } catch (UniqueConstraintViolationException $e) {
                $this->addFlash('error', 'The id already exists. Please choose a different one.');
            }

            $entityManager->persist($vehicule);
            $entityManager->flush();




            try {
                $entityManager->persist($vehicule);
                $entityManager->flush();
                return $this->redirectToRoute('app_vehicule_contoller_index', [], Response::HTTP_SEE_OTHER);
            } catch (UniqueConstraintViolationException $e) {
                $this->addFlash('error', 'The immatriculation already exists. Please choose a different one.');
            }
        }



        return $this->renderForm('vehicule/new.html.twig', [
            'vehicule' => $vehicule,
            'form' => $form,
        ]);
    }

    #[Route('/new', name: 'app_vehicule_contoller_new', methods: ['GET', 'POST'])]
    public function ajouterVehiculeS( Request $request, EntityManagerInterface $entityManager): Response

    {
        $vehicule = new Vehicule();

        $marqueChoices = [
            'Voiture' => ['Audi', 'BMW', 'Chevrolet', 'Ferrari', 'Ford', 'Hyundai', 'Mercedes', 'Toyota', 'Volkswagen'],
            'Van' => ['Mercedes', 'Ford', 'Chevrolet', 'Dodge', 'Nissan', 'RAM', 'Toyota', 'Volkswagen', 'Fiat', 'GMC'],
            'Camion' => ['Ford', 'Chevrolet', 'RAM', 'GMC', 'Toyota', 'Nissan', 'Jeep', 'Dodge'],
            'Bus' => ['Blue Bird', 'Thomas Built Buses', 'Gillig', 'New Flyer', 'Prevost', 'Mci', 'Van Hool']
        ];

        $flatMarqueChoices = [];
        foreach ($marqueChoices as $type => $marques) {
            foreach ($marques as $marque) {
                $flatMarqueChoices[$marque] = $marque;
            }
        }

        $form = $this->createForm(VehiculeType::class, $vehicule, [
            'marque_choices' => $flatMarqueChoices,
        ]);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {


            /** @var UploadedFile $imageFile */
            $imageFile = $form->get('imagev')->getData();

            if ($imageFile) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = $vehicule->getId() . '-' . uniqid() . '.' . $imageFile->guessExtension();

                try {
                    $imageFile->move(
                        $this->getParameter('imagev_directory'),
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }



                $vehicule->setImagev($newFilename);
            }





            try {
                $entityManager->persist($vehicule);
                $entityManager->flush();

                return $this->redirectToRoute('app_vehicule_contoller_index', [], Response::HTTP_SEE_OTHER);
            } catch (UniqueConstraintViolationException $e) {
                $this->addFlash('error', 'The immatriculation already exists. Please choose a different one.');
            }

            $entityManager->persist($vehicule);
            $entityManager->flush();




            try {
                $entityManager->persist($vehicule);
                $entityManager->flush();
                return $this->redirectToRoute('app_vehicule_contoller_index', [], Response::HTTP_SEE_OTHER);
            } catch (UniqueConstraintViolationException $e) {
                $this->addFlash('error', 'The immatriculation already exists. Please choose a different one.');
            }
        }



        return $this->renderForm('vehicule/new.html.twig', [
            'vehicule' => $vehicule,
            'form' => $form,
        ]);
    }

    #[Route('/offre/{id_conducteur}', name: 'app_vehicule_contoller_offre', methods: ['GET'])]

    public function offreVehicules(ManagerRegistry $managerRegistry,  SessionInterface $session): Response
    {
        // Find the vehicles with null idConducteur

        $vehicules = $managerRegistry->getRepository(Vehicule::class)->findBy(['idConducteur' => null]);
        $conducteur_id = $session->get('conducteur_id');

        // Render the vehicles list for the conducteur
        return $this->render('home/offre_vehicules.html.twig', [
            'vehicules' => $vehicules,
            'conducteur_id' => $conducteur_id,
        ]);
    }



    #[Route('/choose/{id}/{id_conducteur}', name: 'app_vehicule_contoller_choose', methods: ['GET'])]
    public function chooseVehicule(string $id, int $id_conducteur, EntityManagerInterface $entityManager): Response
    {
        $vehicule = $entityManager->getRepository(Vehicule::class)->findOneBy(['id' => $id]);

        if (!$vehicule) {
            throw $this->createNotFoundException('The vehicle does not exist');
        }

        $conducteur = $entityManager->getRepository(Conducteur::class)->find($id_conducteur);

        if (!$conducteur) {
            throw $this->createNotFoundException('The conductor does not exist');
        }

        $vehicule->setConducteur($conducteur);

        // Add this line to check if the Conducteur is being set properly
        if ($vehicule->getConducteur() === null) {
            throw new \Exception('Failed to set Conducteur for Vehicule');
        }

        $entityManager->flush();

        return $this->redirectToRoute('app_vehicule_contoller_offre', ['id_conducteur' => $id_conducteur], Response::HTTP_SEE_OTHER);
    }



    #[Route('/available-vehicles/{id_conducteur}', name: 'available_vehicles')]
    public function showAvailableVehicles($id_conducteur, EntityManagerInterface $entityManager)
    {
        // Fetch available vehicles from the database (replace this with your actual query)
        $repository = $entityManager->getRepository(Vehicule::class);
        $vehicules = $repository->findAllAvailable();

        // Pass the 'vehicules' variable to the template
        return $this->render('home/offre_vehicules.html.twig', [
            'vehicules' => $vehicules,
            'id_conducteur' => $id_conducteur,
        ]);
    }


    #[Route('/{id}', name: 'app_vehicule_contoller_show', methods: ['GET'])]
    public function show(Vehicule $vehicule): Response
    {
        return $this->render('vehicule/show.html.twig', [
            'vehicule' => $vehicule,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_vehicule_contoller_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Vehicule $vehicule, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(VehiculeType::class, $vehicule);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_vehicule_contoller_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('vehicule/edit.html.twig', [
            'vehicule' => $vehicule,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_vehicule_contoller_delete', methods: ['POST'])]
    public function delete(Request $request, Vehicule $vehicule, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete' . $vehicule->getId(), $request->request->get('_token'))) {
            $entityManager->remove($vehicule);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_vehicule_contoller_index', [], Response::HTTP_SEE_OTHER);
    }
}
