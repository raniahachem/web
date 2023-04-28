<?php

namespace App\Controller;

use App\Entity\Offre;

use App\Entity\Reservation;
use App\Form\ReservationType;
use App\Repository\ReservationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

use Symfony\Component\Mime\Email;
use App\Service\MailerService;
use Symfony\Component\Mailer\MailerInterface;


#[Route('/reservation')]
class ReservationController extends AbstractController
{
    #[Route('/', name: 'app_reservation_index', methods: ['GET'])]
    public function index(ReservationRepository $reservationRepository): Response
    {
        return $this->render('reservation/index.html.twig', [
            'reservations' => $reservationRepository->findAll(),
        ]);
    }

    #[Route('/listReservation', name: 'app_reservation_index_Client', methods: ['GET'])]
    public function indexClient(ReservationRepository $reservationRepository): Response
    {
        return $this->render('reservation/indexClient.html.twig', [
            'reservations' => $reservationRepository->findAll(),
        ]);
    }
    #[Route('/listReservation/{idClient}', name: 'app_reservation_index_Clientid', methods: ['GET'])]
    public function indexClientid(ReservationRepository $reservationRepository, $idClient): Response
    {
        return $this->render('reservation/indexClient.html.twig', [
            'reservations' => $reservationRepository->findByIdClient($idClient),
        ]);
    }

    #[Route('/new', name: 'app_reservation_new', methods: ['GET', 'POST'])]
    public function new(Request $request, ReservationRepository $reservationRepository): Response
    {
        $reservation = new Reservation();
        $form = $this->createForm(ReservationType::class, $reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reservationRepository->save($reservation, true);

            return $this->redirectToRoute('app_reservation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('reservation/new.html.twig', [
            'reservation' => $reservation,
            'form' => $form,
        ]);
    }

    #[Route('front/new', name: 'app_reservation_new_front', methods: ['GET', 'POST'])]
    public function newfront(Request $request, ReservationRepository $reservationRepository): Response
    {
        $reservation = new Reservation();
        $form = $this->createForm(OffreType::class, $offre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reservationRepository->save($reservation, true);

            return $this->redirectToRoute('app_reservation_index_Client', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('reservation/newfront.html.twig', [
            'reservation' => $reservation,
            'form' => $form,
        ]);
    }

    #[Route('/reserverfront/new', name: 'app_reservation_new_reserver', methods: ['GET', 'POST'])]
    public function newfrontreserver(Request $request, ReservationRepository $reservationRepository,MailerInterface $mailer): Response
    {
        $reservation = new Reservation();
        $form = $this->createForm(ReservationType::class, $reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reservationRepository->saveO($reservation,$mailer, true);

            return $this->redirectToRoute('app_reservation_index_Client', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('reservation/newfront.html.twig', [
            'reservation' => $reservation,
            'form' => $form,
        ]);
    }

     
   


    #[Route('/{idReservation}', name: 'app_reservation_show', methods: ['GET'])]
    public function show(Reservation $reservation): Response
    {
        return $this->render('reservation/show.html.twig', [
            'reservation' => $reservation,
        ]);
    }
    #[Route('/{idReservation}/showfront', name: 'app_reservation_showfront', methods: ['GET'])]
    public function showfront(Reservation $reservation): Response
    {
        return $this->render('reservation/showfront.html.twig', [
            'reservation' => $reservation,
        ]);
    }

    #[Route('/{idReservation}/edit', name: 'app_reservation_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Reservation $reservation, ReservationRepository $reservationRepository): Response
    {
        $form = $this->createForm(ReservationType::class, $reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reservationRepository->save($reservation, true);

            return $this->redirectToRoute('app_reservation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('reservation/edit.html.twig', [
            'reservation' => $reservation,
            'form' => $form,
        ]);
    }
    #[Route('editfront/{idReservation}/', name: 'app_reservation_editfront', methods: ['GET', 'POST'])]
    public function editfront(Request $request, Reservation $reservation, ReservationRepository $reservationRepository): Response
    {
        $form = $this->createForm(ReservationType::class, $reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reservationRepository->save($reservation, true);

            return $this->redirectToRoute('app_reservation_index_Client', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('reservation/editFront.html.twig', [
            'reservation' => $reservation,
            'form' => $form,
        ]);
    }

     
     




    #[Route('/{idReservation}', name: 'app_reservation_delete', methods: ['POST'])]
    public function delete(Request $request, Reservation $reservation, ReservationRepository $reservationRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reservation->getIdReservation(), $request->request->get('_token'))) {
            $reservationRepository->remove($reservation, true);
        }

        return $this->redirectToRoute('app_reservation_index', [], Response::HTTP_SEE_OTHER);
    }
     
    #[Route('/stats', name: 'app_reservation_stats', methods: ['POST'])]
    public function statistiques(){
        return $this->render('offre/stats.html.twig');
    }
}
