<?php
// Se connecter à la base de données
$pdo = new PDO('mysql:host=localhost;dbname=final', 'root', '');

// Récupérer les données des types de véhicules et leurs quantités correspondantes depuis la base de données
$stmt = $pdo->query('SELECT type_vehicule, COUNT(*) AS quantite FROM Offre GROUP BY type_vehicule');
$vehicules = $stmt->fetchAll(PDO::FETCH_ASSOC);

// Créer un tableau associatif pour stocker les couleurs correspondantes à chaque type de véhicule
$couleurs = [
    'bus' => '#ff6384',
    'voiture' => '#36a2eb',
    'van' => '#ffce56'
];

// Initialiser le HTML pour les carrés colorés
$carrés_html = '';

// Parcourir les types de véhicules et construire le HTML pour les carrés colorés
foreach ($vehicules as $vehicule) {
    $type_vehicule = strtolower($vehicule['type_vehicule']);
    if (isset($couleurs[$type_vehicule])) {
        $couleur = $couleurs[$type_vehicule];
         
    }
}

// Créer une nouvelle charte à secteurs en utilisant Chart.js
?>
<!DOCTYPE html>
<html>
  <head>
    <title>Exemple de charte à secteurs</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
   
    <style>
        canvas{
            max-width: 1000px;
            max-height: 800px;
            margin: auto;
            display: block;
        }
    </style>
  </head>
  <body>
    <div style="text-align:center">
        <h3>Les statistiques des types des véhicules disponibles dans les offres</h3>
        <?php echo $carrés_html; ?>
    </div>

    <canvas id="chart" ></canvas>
    <script>
      const labels = <?php echo json_encode(array_column($vehicules, 'type_vehicule')); ?>;
      const data = <?php echo json_encode(array_column($vehicules, 'quantite')); ?>;
      const chart = new Chart(document.getElementById("chart"), {
        type: "pie",
        data: {
          labels: labels,
          datasets: [
            {
              label: "Pourcentage des types de véhicules",
              data: data,
              backgroundColor: <?php echo json_encode(array_values($couleurs)); ?>,
            },
          ],
        },
        
      });
      
    </script>
    <button>back to list</button>
  </body>
</html>
