<?php

namespace App\Entity;

use App\Repository\OffreRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: OffreRepository::class)]
class Offre
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    private ?string $Destination = null;

    #[ORM\Column(length: 255)]
    private ?string $pt_depart = null;

    #[ORM\Column]
    private ?float $prix = null;

    #[ORM\Column(length: 255)]
    private ?string $type_vehicule = null;

    /* #[ORM\ManyToOne(inversedBy: 'offres')]
    #[ORM\JoinColumn(name: 'id_conducteur_id', referencedColumnName: 'id_conducteur')]
    private ?Conducteur $id_conducteur_id = null;

    #[ORM\OneToOne(mappedBy: 'id_offre', cascade: ['persist', 'remove'])]
    private ?Reservation $reservation = null; */
    #[ORM\ManyToOne(inversedBy: 'offres')]
    #[ORM\JoinColumn(name: 'id_conducteur_id', referencedColumnName: 'id_conducteur')]
    private ?Conducteur $id_conducteur_id = null;

    #[ORM\OneToOne(mappedBy: 'id_offre', cascade: ['persist', 'remove'])]
    private ?Reservation $reservation = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDestination(): ?string
    {
        return $this->Destination;
    }

    public function setDestination(string $Destination): self
    {
        $this->Destination = $Destination;

        return $this;
    }

    public function getPtDepart(): ?string
    {
        return $this->pt_depart;
    }

    public function setPtDepart(string $pt_depart): self
    {
        $this->pt_depart = $pt_depart;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getTypeVehicule(): ?string
    {
        return $this->type_vehicule;
    }

    public function setTypeVehicule(string $type_vehicule): self
    {
        $this->type_vehicule = $type_vehicule;

        return $this;
    }

    public function getIdConducteur(): ?Conducteur
    {
        return $this->id_conducteur_id;
    }

    public function setIdConducteur(?Conducteur $id_conducteur): self
    {
        $this->id_conducteur_id = $id_conducteur;

        return $this;
    }

    public function getReservation(): ?Reservation
    {
        return $this->reservation;
    }

    public function setReservation(Reservation $reservation): self
    {
        // set the owning side of the relation if necessary
        if ($reservation->getIdOffre() !== $this) {
            $reservation->setIdOffre($this);
        }

        $this->reservation = $reservation;

        return $this;
    }

    /* public function getIdConducteur(): ?Conducteur
    {
        return $this->id_conducteur_id;
    }

    public function setIdConducteur(?Conducteur $id_conducteur): self
    {
        $this->id_conducteur_id = $id_conducteur;

        return $this;
    }

    public function getReservation(): ?Reservation
    {
        return $this->reservation;
    }

    public function setReservation(Reservation $reservation): self
    {
        // set the owning side of the relation if necessary
        if ($reservation->getIdOffre() !== $this) {
            $reservation->setIdOffre($this);
        }

        $this->reservation = $reservation;

        return $this;
    } */

    public function getIdConducteurId(): ?Conducteur
    {
        return $this->id_conducteur_id;
    }

    public function setIdConducteurId(?Conducteur $id_conducteur_id): self
    {
        $this->id_conducteur_id = $id_conducteur_id;

        return $this;
    }
}
