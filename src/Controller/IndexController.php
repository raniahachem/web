<?php
namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use PDO;
use App\Entity\Offre;

class IndexController extends AbstractController
{

    #[Route('/stats', name: 'stats')]
    public function stats(): Response
    {
        // Se connecter à la base de données
        $pdo = new PDO('mysql:host=localhost;dbname=autoxpress', 'root', '');
        // Récupérer les données des types de véhicules et leurs quantités correspondantes depuis la base de données
        $stmt = $pdo->query('SELECT Type_vehicule, COUNT(*) AS quantite FROM Offre GROUP BY Type_vehicule');
        $vehicules = $stmt->fetchAll(PDO::FETCH_ASSOC);

        // Créer un tableau des noms de types de véhicules et des quantités correspondantes
        $labels = array_column($vehicules, 'Type_vehicule');
        $data = array_column($vehicules, 'quantite');

        // Afficher la vue avec les données de statistiques
        return $this->render('stats.html.twig', [
            'labels' => $labels,
            'data' => $data,
        ]);
    }
}
