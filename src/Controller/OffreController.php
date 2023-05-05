<?php

namespace App\Controller;

use App\Entity\Offre;
use App\Form\OffreType;
use App\Repository\OffreRepository;
use App\Repository\ReservationRepository;
use App\Service\MailerService;
use App\Service\CommentMailer;
 

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


use Knp\Bundle\PaginatorBundle\Pagination\SlidingPaginationInterface;
use Knp\Bundle\PaginatorBundle\Twig\Extension\PaginationExtension;
use Knp\Component\Pager\PaginatorInterface;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;

use Symfony\Component\Mime\Email;
use Symfony\Component\Mailer\MailerInterface;

use Symfony\Component\HttpFoundation\JsonResponse;

#[Route('/offre')]
class OffreController extends AbstractController
{
    #[Route('/', name: 'app_offre_index', methods: ['GET'])]
    public function index(OffreRepository $offreRepository,Request $request, PaginatorInterface $paginator): Response
    {
       
        $offres = $offreRepository->findAll();
        $offres = $paginator->paginate(
            $offres,
            $request->query->getInt('page', 1),
            2
        );

        return $this->render('offre/index.html.twig', [
            'offres' => $offres,
        ]);
    }

    #[Route('/admin', name: 'app_offre_index_admin', methods: ['GET'])]
    public function indexAdmin(OffreRepository $offreRepository): Response
    { 
         
        return $this->render('offre/indexAdmin.html.twig', [
            'offres' => $offreRepository->findAll(),
        ]);
 
    }

    #[Route('/listOffre', name: 'app_offre_index_Client', methods: ['GET'])]
    public function indexClient(OffreRepository $offreRepository): Response
    {
    $offres = $offreRepository->findAll();
      
    return $this->render('offre/indexClient2.html.twig', [
        'offres' => $offres,
         ]);
  }

    #[Route('/new', name: 'app_offre_new', methods: ['GET', 'POST'])]
    public function new(Request $request, OffreRepository $offreRepository,MailerInterface $mailer): Response
    {
        $offre = new Offre();
        $form = $this->createForm(OffreType::class, $offre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $offreRepository->save($offre,$mailer, true);


            // $commentMailer->sendEmailOffre($offre);
            return $this->redirectToRoute('app_offre_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('offre/new.html.twig', [
            'offre' => $offre,
            'form' => $form,
        ]);
    }

    #[Route('front/new', name: 'app_offre_new_front', methods: ['GET', 'POST'])]
    public function newfront(Request $request, OffreRepository $offreRepository): Response
    {
         
             
        $offre = new Offre();
        
        $form = $this->createForm(OffreType::class, $offre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $offreRepository->save($offre, true);
            return $this->redirectToRoute('app_offre_index_Client', [], Response::HTTP_SEE_OTHER);
        }
        return $this->renderForm('offre/newfront.html.twig', [
            'offre' => $offre,
            'form' => $form,
        ]);
    }


    #[Route('reserver/new', name: 'app_offre_new_reserver', methods: ['GET', 'POST'])]
    public function newreserver(Request $request, OffreRepository $offreRepository): Response
    {
         
             
        $offre = new Offre();
        
        $form = $this->createForm(OffreType::class, $offre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $offreRepository->save($offre, true);

           
            return $this->redirectToRoute('app_offre_index_Client', [], Response::HTTP_SEE_OTHER);
        }
        return $this->renderForm('offre/newadmin.html.twig', [
            'offre' => $offre,
            'form' => $form,
        ]);
    }


    #[Route('/{id}', name: 'app_offre_show', methods: ['GET'])]
    public function show(Offre $offre): Response
    {
        return $this->render('offre/show.html.twig', [
            'offre' => $offre,
        ]);
    }

    #[Route('/front/{id}', name: 'app_offre_show_client', methods: ['GET'])]
    public function showfront(Offre $offre): Response
    {
      
        return $this->render('offre/showfront.html.twig', [
            'offre' => $offre,
        ]);
    }

    #[Route('/admin/{id}', name: 'app_offre_show_admin', methods: ['GET'])]
    public function showAdmin(Offre $offre): Response
    {
      
        return $this->render('offre/showAdmin.html.twig', [
            'offre' => $offre,
        ]);
    }



    #[Route('/{id}/edit', name: 'app_offre_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Offre $offre, OffreRepository $offreRepository): Response
    {
        $form = $this->createForm(OffreType::class, $offre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $offreRepository->save2($offre, true);

            return $this->redirectToRoute('app_offre_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('offre/edit.html.twig', [
            'offre' => $offre,
            'form' => $form,
        ]);
    }

    #[Route('/editfront/{id}', name: 'app_offre_edit_front', methods: ['GET', 'POST'])]
    public function editfront(Request $request, Offre $offre, OffreRepository $offreRepository, MailerService $mailer
    ): Response
    {
        $form = $this->createForm(OffreType::class, $offre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $offreRepository->save($offre, true);
            return $this->redirectToRoute('app_offre_indexClient', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('offre/editfront.html.twig', [
            'offre' => $offre,
            'form' => $form,
        ]);
    }




    #[Route('/{id}', name: 'app_offre_delete', methods: ['POST'])]
    public function delete(Request $request, Offre $offre, OffreRepository $offreRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$offre->getId(), $request->request->get('_token'))) {
            $offreRepository->remove($offre, true);
        }

        return $this->redirectToRoute('app_offre_index', [], Response::HTTP_SEE_OTHER);
    }

    #[Route('offre/typeVehiculecl/{typeVehicule}', name: 'typeVehiculeClient', methods: ['GET'])]
public function indextypeVehiculeC(OffreRepository $offreRepository, $typeVehicule): Response
{
    $offres = $offreRepository->findByTypeVehicule($typeVehicule);
    return $this->render('offre/indexClient2.html.twig', [
        'offres' => $offres,
    ]);
}
     
}
