<?php

namespace App\Controller;

use App\Entity\Reservation;
use App\Entity\Client;
use App\Entity\Offre;
 use App\Service\CommentMailer;
use App\Form\ReservationType;
use App\Repository\ReservationRepository;
use App\Repository\OffreRepository;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

use Knp\Component\Pager\PaginatorInterface;

use Symfony\Component\Mime\Email;
use App\Service\MailerService;
use Symfony\Component\Mailer\MailerInterface;
use Knp\Bundle\PaginatorBundle\Pagination\SlidingPaginationInterface;
use Knp\Bundle\PaginatorBundle\Twig\Extension\PaginationExtension;

#[Route('/reservation')]
class ReservationController extends AbstractController
{
    #[Route('/', name: 'app_reservation1_index', methods: ['GET'])]
    public function index(ReservationRepository $reservationRepository, Request $request, PaginatorInterface $paginator): Response
    {
         
            $reservations = $reservationRepository->findAll();
            $reservations = $paginator->paginate(
                $reservations,
                $request->query->getInt('page', 1),
                1
            );
            return $this->render('reservation/index.html.twig', [
                'reservations' => $reservations,
    
            ]);
             
        
    }

    #[Route('/reservation', name: 'app_reservation_index', methods: ['GET'])]
    public function index2(ReservationRepository $reservationRepository, Request $request, PaginatorInterface $paginator): Response
    {
         
            $reservations = $reservationRepository->findAll();
            $reservations = $paginator->paginate(
                $reservations,
                $request->query->getInt('page', 1),
                1
            );
            return $this->render('reservation/index.html.twig', [
                'reservations' => $reservations,
    
            ]);
             
        
    }


    #[Route('/listReservation', name: 'app_reservation_index_Client', methods: ['GET'])]
    public function indexClient(ReservationRepository $reservationRepository): Response
    {
        return $this->render('reservation/indexClient.html.twig', [
            'reservations' => $reservationRepository->findAll(),
        ]);
    }

    #[Route('/listReservation/{id_client_id}', name: 'app_reservation_index_Clientid', methods: ['GET'])]
    public function indexClientid(ReservationRepository $reservationRepository, $id_client_id): Response
    {
        return $this->render('reservation/indexClient.html.twig', [
            'reservations' => $reservationRepository->findBy(['id_client_id' => $id_client_id]),
        ]);
    }

    #[Route('/new', name: 'app_reservation_new', methods: ['GET', 'POST'])]
    public function new(Request $request, ReservationRepository $reservationRepository ): Response
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
    public function newfrontreserver(Request $request, ReservationRepository $reservationRepository,OffreRepository $offreRepository,CommentMailer $commentMailer): Response
    {
        $reservation = new Reservation();
        $offre = new Offre();
        $form = $this->createForm(ReservationType::class, $reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reservationRepository->saveO($reservation,  true);

            $commentMailer->reserverOffre($reservation);
            // $commentMailer->reserverOffre($offre);
            return $this->redirectToRoute('app_reservation_index_Client', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('reservation/newfront.html.twig', [
            'reservation' => $reservation,
            'form' => $form,
        ]);
    }

     
   


    #[Route('/{id}', name: 'app_reservation_show', methods: ['GET'])]
    public function show(Reservation $reservation): Response
    {
        return $this->render('reservation/show.html.twig', [
            'reservation' => $reservation,
        ]);
    }

    #[Route('/{id}/showfront', name: 'app_reservation_showfront', methods: ['GET'])]
    public function showfront(Reservation $reservation): Response
    {
        return $this->render('reservation/showfront.html.twig', [
            'reservation' => $reservation,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_reservation_edit', methods: ['GET', 'POST'])]
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

    #[Route('editfront/{id}/', name: 'app_reservation_editfront', methods: ['GET', 'POST'])]
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

    #[Route('/{id}', name: 'app_reservation_delete', methods: ['POST'])]
    public function delete(Request $request, Reservation $reservation,  ReservationRepository $reservationRepository,  CommentMailer $commentMailer): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reservation->getId(), $request->request->get('_token'))) {
            $reservationRepository->remove($reservation,  true);
        }

        $commentMailer->sendEmailOffreremove($reservation );
        return $this->redirectToRoute('app_reservation_index_Client', [], Response::HTTP_SEE_OTHER);
    }
     
    #[Route('/stats', name: 'app_reservation_stats', methods: ['POST'])]
    public function statistiques(){
        return $this->render('offre/stats.html.twig');
    }
}
