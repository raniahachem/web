<?php

namespace App\Controller;


use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use App\Repository\AvisRepository;
use App\Entity\Avis;
use App\Entity\Commentaire;
use App\Entity\Conducteur;
use App\Repository\ConducteurRepository;
use App\Entity\Client;
use App\Repository\ClientRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;

use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;

class DashboardController extends AbstractController
{
    #[Route('/dashboard', name: 'app_dashboard')]
    public function chartavis(ManagerRegistry $doctrine): Response
    {
        $avisRepo = $doctrine->getRepository(Avis::class);
        $avis = $avisRepo->findAll();
    
        $clients = $doctrine->getRepository(Client::class)->findAll();
    
        $ratingsData = [
            'labels' => ['0', '1', '2', '3', '4', '5'],
            'datasets' => [
                [
                    'label' => 'Number of clients',
                    'backgroundColor' => '#007bff',
                    'data' => [0, 0, 0, 0, 0, 0],
                ],
            ],
        ];
    
        foreach ($avis as $avi) {
            $rating = $avi->getRating();
            $ratingsData['datasets'][0]['data'][$rating] += 1;
        }
    
        return $this->render('dashboard/dashboard.html.twig', [
            'controller_name' => 'DashboardController',
            'ratingsData' => json_encode($ratingsData['datasets'][0]['data']),
            'clients' => $clients,
            'avis' => $avis,
        ]);
    }
    

}
