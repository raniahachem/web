<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

/**
 * HistoriqueReservation
 *
 * @ORM\Table(name="historique_reservation")
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
     * @var int|null
     *
     * @ORM\Column(name="id_conducteur", type="integer", nullable=true)
     */
    private $idConducteur;

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
     * @var int|null
     *
     * @ORM\Column(name="id_client", type="integer", nullable=true)
     */
    private $idClient;

    public function getIdHistoriqueReservation(): ?int
    {
        return $this->idHistoriqueReservation;
    }

    public function getIdReservation(): ?int
    {
        return $this->idReservation;
    }

    public function setIdReservation(int $idReservation): self
    {
        $this->idReservation = $idReservation;

        return $this;
    }

    public function getIdConducteur(): ?int
    {
        return $this->idConducteur;
    }

    public function setIdConducteur(?int $idConducteur): self
    {
        $this->idConducteur = $idConducteur;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(?\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getDateDepartReelle(): ?\DateTimeInterface
    {
        return $this->dateDepartReelle;
    }

    public function setDateDepartReelle(?\DateTimeInterface $dateDepartReelle): self
    {
        $this->dateDepartReelle = $dateDepartReelle;

        return $this;
    }

    public function getDateArriveReelle(): ?\DateTimeInterface
    {
        return $this->dateArriveReelle;
    }

    public function setDateArriveReelle(?\DateTimeInterface $dateArriveReelle): self
    {
        $this->dateArriveReelle = $dateArriveReelle;

        return $this;
    }

    public function getLieuDepart(): ?string
    {
        return $this->lieuDepart;
    }

    public function setLieuDepart(?string $lieuDepart): self
    {
        $this->lieuDepart = $lieuDepart;

        return $this;
    }

    public function getLieuDestination(): ?string
    {
        return $this->lieuDestination;
    }

    public function setLieuDestination(?string $lieuDestination): self
    {
        $this->lieuDestination = $lieuDestination;

        return $this;
    }

    public function getAvisClient(): ?string
    {
        return $this->avisClient;
    }

    public function setAvisClient(?string $avisClient): self
    {
        $this->avisClient = $avisClient;

        return $this;
    }

    public function getStatusReservation(): ?string
    {
        return $this->statusReservation;
    }

    public function setStatusReservation(?string $statusReservation): self
    {
        $this->statusReservation = $statusReservation;

        return $this;
    }

    public function getIdClient(): ?int
    {
        return $this->idClient;
    }

    public function setIdClient(?int $idClient): self
    {
        $this->idClient = $idClient;

        return $this;
    }


}
