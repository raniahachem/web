<?php

namespace App\Controller;

use App\Entity\Message;
use App\Entity\Payement;
use App\Form\MessageType;
use App\Form\PaymentType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/payment')]
class PaymentController extends AbstractController
{
    #[Route('/', name: 'payment_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {

        $ab = $entityManager
            ->getRepository(Payement::class)
            ->findAll();


        return $this->render(
            'payements/index.html.twig',
            [
                'ab' => $ab,
            ]
        );
    }
    #[Route('/{id}/edit', name: 'payment_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Payement $pay, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(PaymentType::class, $pay);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('payment_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('payements/edit.html.twig', [
            'pay' => $pay,
            'form' => $form,
        ]);
    }
   /* #[Route('/{id}/delete', name: 'pay_delete', methods: ['GET'])]
    public function delete(Request $request, Payement $message, EntityManagerInterface $entityManager): Response
    {

        $entityManager->remove($message);
        $entityManager->flush();


        return $this->redirectToRoute('payment_index', [], Response::HTTP_SEE_OTHER);
    }*/
    #[Route('/add', name: 'pay_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $message = new Payement();
        $form = $this->createForm(PaymentType::class, $message);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($message);
            $entityManager->flush();

            return $this->redirectToRoute('payment_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('payements/add.html.twig', [
            'message' => $message,
            'form' => $form,
        ]);
    }





    #[Route('/all', name: 'payment_all', methods: ['GET'])]
    public function indexFront(EntityManagerInterface $entityManager): Response
    {

        $ab = $entityManager
            ->getRepository(Payement::class)
            ->findAll();


        return $this->render(
            'payements/indexFront.html.twig',
            [
                'ab' => $ab,
            ]
        );
    }
    #[Route('/update/{id}', name: 'payment_update', methods: ['GET', 'POST'])]
    public function editFront(Request $request, Payement $pay, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(PaymentType::class, $pay);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('payment_all', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('payements/editFront.html.twig', [
            'pay' => $pay,
            'form' => $form,
        ]);
    }
    #[Route('/delete/{id}', name: 'pay_delete', methods: ['GET'])]
    public function deleteFront(Request $request, Payement $message, EntityManagerInterface $entityManager): Response
    {

        $entityManager->remove($message);
        $entityManager->flush();


        return $this->redirectToRoute('payment_all', [], Response::HTTP_SEE_OTHER);
    }
    #[Route('/create', name: 'pay_create', methods: ['GET', 'POST'])]
    public function newFront(Request $request, EntityManagerInterface $entityManager): Response
    {
        $message = new Payement();
        $form = $this->createForm(PaymentType::class, $message);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($message);
            $entityManager->flush();

            return $this->redirectToRoute('payment_all', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('payements/newFront.html.twig', [
            'message' => $message,
            'form' => $form,
        ]);    
}

}
