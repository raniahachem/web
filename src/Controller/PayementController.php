<?php

namespace App\Controller;

use App\Entity\Payement;
use App\Form\PayementType;
use App\Repository\PayementRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/payement')]
class PayementController extends AbstractController
{
    #[Route('/', name: 'app_payement_index', methods: ['GET'])]
    public function index(PayementRepository $payementRepository): Response
    {
        return $this->render('payement/index.html.twig', [
            'payements' => $payementRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_payement_new', methods: ['GET', 'POST'])]
    public function new(Request $request, PayementRepository $payementRepository): Response
    {
        $payement = new Payement();
        $form = $this->createForm(PayementType::class, $payement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $payementRepository->save($payement, true);

            return $this->redirectToRoute('app_payement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('payement/new.html.twig', [
            'payement' => $payement,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_payement_show', methods: ['GET'])]
    public function show(Payement $payement): Response
    {
        return $this->render('payement/show.html.twig', [
            'payement' => $payement,
        ]);
    }

    #[Route('/savep/{clientId}/{modePayment}/{prixCourse}', name: 'app_payement_savep')]
    public function save(Request $request,$clientId,$modePayment,$prixCourse, PayementRepository $payementRepository): Response
    {
        $payement = new Payement();
        $payement->setClient($clientId);
        $payement->setModePayment($modePayment);
        $payement->setPrix($prixCourse);
        $payement->setPaymentDate(new \DateTime());

        $payementRepository->save($payement, true);

        return $this->redirectToRoute('app_payement_index', [], Response::HTTP_SEE_OTHER);//
    }

    #[Route('/{id}/edit', name: 'app_payement_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Payement $payement, PayementRepository $payementRepository): Response
    {
        $form = $this->createForm(PayementType::class, $payement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $payementRepository->save($payement, true);

            return $this->redirectToRoute('app_payement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('payement/edit.html.twig', [
            'payement' => $payement,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_payement_delete', methods: ['POST'])]
    public function delete(Request $request, Payement $payement, PayementRepository $payementRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$payement->getId(), $request->request->get('_token'))) {
            $payementRepository->remove($payement, true);
        }

        return $this->redirectToRoute('app_payement_index', [], Response::HTTP_SEE_OTHER);
    }
}
