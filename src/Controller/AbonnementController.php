<?php

namespace App\Controller;

use App\Entity\Abonnement;
use App\Entity\Message;
use App\Form\AbonnementsType;
use App\Form\MessageType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/abonements')]
class AbonnementController extends AbstractController
{
    #[Route('/', name: 'abonement_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $ab = $entityManager
            ->getRepository(Abonnement::class)
            ->findAll();


        return $this->render(
            'abonnements/index.html.twig',
            [
                'ab' => $ab,
            ]
        );
    }

    #[Route('/add', name: 'ab_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $message = new Abonnement();
        $form = $this->createForm(AbonnementsType::class, $message);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($message);
            $entityManager->flush();

            return $this->redirectToRoute('abonement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('abonnements/add.html.twig', [
            'message' => $message,
            'form' => $form,
        ]);
    }

    

    #[Route('/{id}/edit', name: 'ab_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Abonnement $ab, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(AbonnementsType::class, $ab);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('abonement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('abonnements/edit.html.twig', [
            'ab' => $ab,
            'form' => $form,
        ]);
    }




    // Hedhy el khedma eli rakahthelek eni 



    #[Route('/create', name: 'ab_create', methods: ['GET', 'POST'])]
    public function createFront(Request $request, EntityManagerInterface $entityManager): Response
    {
        $message = new Abonnement();
        $form = $this->createForm(AbonnementsType::class, $message);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($message);
            $entityManager->flush();

            return $this->redirectToRoute('abonement_all', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('abonnements/addFront.html.twig', [
            'message' => $message,
            'form' => $form,
        ]);
    }
    #[Route('/all', name: 'abonement_all', methods: ['GET'])]
    public function getAll(EntityManagerInterface $entityManager): Response
    {
        $ab = $entityManager
            ->getRepository(Abonnement::class)
            ->findAll();


        return $this->render(
            'abonnements/listFront.html.twig',
            [
                'ab' => $ab,
            ]
        );
    }
    #[Route('/{id}', name: 'ab_update', methods: ['GET', 'POST'])]
    public function update(Request $request, Abonnement $ab, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(AbonnementsType::class, $ab);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('abonement_all', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('abonnements/update.html.twig', [
            'ab' => $ab,
            'form' => $form,
        ]);
    }

    #[Route('/{id}/delete', name: 'ab_delete', methods: ['GET'])]
    public function delete(Request $request, Abonnement $message, EntityManagerInterface $entityManager): Response
    {

        $entityManager->remove($message);
        $entityManager->flush();


        return $this->redirectToRoute('abonement_all', [], Response::HTTP_SEE_OTHER);
    }
}
