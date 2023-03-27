<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * HistoriqueReservation
 *
 * @ORM\Table(name="historique_reservation", indexes={@ORM\Index(name="id_client", columns={"id_client"}), @ORM\Index(name="id_conducteur", columns={"id_conducteur"})})
 * @ORM\Entity
 */
class HistoriqueReservation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_historique_reservation", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idHistoriqueReservation;

    /**
     * @var int
     *
     * @ORM\Column(name="id_reservation", type="integer", nullable=false)
     */
    private $idReservation;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="date", type="date", nullable=true, options={"default"="CURRENT_TIMESTAMP"})
     */
    private $date = 'CURRENT_TIMESTAMP';

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="date_depart_reelle", type="date", nullable=true, options={"default"="CURRENT_TIMESTAMP"})
     */
    private $dateDepartReelle = 'CURRENT_TIMESTAMP';

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="date_arrive_reelle", type="date", nullable=true, options={"default"="CURRENT_TIMESTAMP"})
     */
    private $dateArriveReelle = 'CURRENT_TIMESTAMP';

    /**
     * @var string|null
     *
     * @ORM\Column(name="lieu_depart", type="string", length=255, nullable=true)
     */
    private $lieuDepart;

    /**
     * @var string|null
     *
     * @ORM\Column(name="lieu_destination", type="string", length=255, nullable=true)
     */
    private $lieuDestination;

    /**
     * @var string|null
     *
     * @ORM\Column(name="avis_client", type="string", length=500, nullable=true)
     */
    private $avisClient;

    /**
     * @var string|null
     *
     * @ORM\Column(name="status_reservation", type="string", length=255, nullable=true)
     */
    private $statusReservation;

    /**
     * @var \Conducteur
     *
     * @ORM\ManyToOne(targetEntity="Conducteur")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_conducteur", referencedColumnName="id_conducteur")
     * })
     */
    private $idConducteur;

    /**
     * @var \Client
     *
     * @ORM\ManyToOne(targetEntity="Client")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_client", referencedColumnName="id_client")
     * })
     */
    private $idClient;


}
