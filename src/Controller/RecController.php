<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Entity\Client;
use App\Form\ReclamationType;
use App\Repository\ReclamationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Normalizer\Normalizer;

#[Route('/')]
class RecController extends AbstractController
{
    #[Route('/aff', name: 'app_rec_index', methods: ['GET'])]
    public function index(ReclamationRepository $reclamationRepository): Response
    {
        return $this->render('rec/index.html.twig', [
            'reclamations' => $reclamationRepository->findAll(),
        ]);
    }

    /*#[Route('/addrec', name: 'app_rec_new', methods: ['GET', 'POST'])]
    public function new(Request $request, ReclamationRepository $reclamationRepository): Response
    {
        $reclamation = new Reclamation();
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            //getSession userid
            $reclamation->setIdClient(3);
            $reclamationRepository->save($reclamation, true);

            return $this->redirectToRoute('app_rec_show', ['id' => $reclamation->getId()]
            , Response::HTTP_SEE_OTHER);
            
        }

        return $this->renderForm('rec/new.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form,
        ]);
    }*/


    #[Route('/addrec/{clientId}', name: 'app_rec_new', methods: ['GET', 'POST'])]
public function new(Request $request, int $clientId): Response
{
    $reclamation = new Reclamation();
    $form = $this->createForm(ReclamationType::class, $reclamation);
    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        $entityManager = $this->getDoctrine()->getManager();

        // Récupérer l'entité Client correspondante à partir de l'ID client
        $client = $this->getDoctrine()->getRepository(Client::class)->find($clientId);

        // Affecter l'entité Client à la propriété IdClient de la réclamation
        $reclamation->setIdClient($client);

        // Sauvegarder l'entité Reclamation
        $entityManager->persist($reclamation);
        $entityManager->flush();

        return $this->redirectToRoute('app_rec_show', ['id' => $reclamation->getId()]);
    }

    return $this->renderForm('rec/new.html.twig', [
        'reclamation' => $reclamation,
        'form' => $form,
    ]);
}

    
    #[Route('/{id}', name: 'app_rec_show', methods: ['GET'])]
    public function show(Reclamation $reclamation): Response
    {
        return $this->render('rec/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }

    #[Route('/recpourclient/{id}', name: 'app_rec_showclient', methods: ['GET'])]
    public function showpourclient(Reclamation $reclamation): Response
    {
        return $this->render('rec/showclient.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_rec_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Reclamation $reclamation, ReclamationRepository $reclamationRepository): Response
    {
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reclamationRepository->save($reclamation, true);

            return $this->redirectToRoute('app_rec_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('rec/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_rec_delete', methods: ['POST'])]
    public function delete(Request $request, Reclamation $reclamation, ReclamationRepository $reclamationRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reclamation->getId(), $request->request->get('_token'))) {
            $reclamationRepository->remove($reclamation, true);
        }

        return $this->redirectToRoute('app_rec_index', [], Response::HTTP_SEE_OTHER);
    }

    #[Route('/{id}/rec', name: 'app_rec_deleteclient', methods: ['POST'])]
    public function deletepourclient(Request $request, Reclamation $reclamation, ReclamationRepository $reclamationRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reclamation->getId(), $request->request->get('_token'))) {
            $reclamationRepository->remove($reclamation, true);
        }

        return $this->redirectToRoute('app_rec_indexclient', [], Response::HTTP_SEE_OTHER);
    }


    #[Route('/order_By_Type', name: 'order_By_Type', methods: ['GET'])]
    public function order_By_Type(ReclamationRepository $reclamationRepository): Response
    {
        $reclamationByType = $reclamationRepository->orderByType();

        return $this->render('rec/index.html.twig', [
            'reclamations' => $reclamationByType,
        ]);
    }


    
    #[Route('/order_By_Date', name: 'order_By_Date', methods: ['GET'])]
    public function order_By_Date(ReclamationRepository $reclamationRepository): Response
    {

        $reclamationByDate = $reclamationRepository->order_By_Date();

        return $this->render('rec/index.html.twig', [
            'reclamations' => $reclamationByDate,
        ]);

    }

    #[Route('/searchRec', name: 'searchRec')]
    public function searchRec(Request $request, ReclamationRepository $repository)
    {
        $requestString = $request->get('searchValue');
        $Reclamations = $repository->findbytype($requestString);
    
        return $this->render('rec/index.html.twig', [
            'reclamations' => $Reclamations,
        ]);
    }
    


    #[Route('/affrecclient/{id}', name: 'app_rec_indexclient')]
public function afficher(Client $client, ReclamationRepository $reclamationRepository): Response
{
    $client = $this->getDoctrine()->getRepository(Client::class)->find(3);
$reclamations = $client->getReclamations();

    
    return $this->render('rec/indexclient.html.twig', [
        'reclamations' => $reclamations,
    ]);
}
    
}
