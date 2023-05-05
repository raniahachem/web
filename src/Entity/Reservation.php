<?php

namespace App\Entity;

use App\Repository\ReservationRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ReservationRepository::class)]
class Reservation
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column]
    private ?int $nb_place = null;

    #[ORM\Column(type: Types::DATE_MUTABLE)]
    private ?\DateTimeInterface $date = null;

    #[ORM\Column(length: 255)]
    private ?string $point_de_depart = null;

    #[ORM\Column(length: 255)]
    private ?string $point_arrive = null;

    #[ORM\ManyToOne(inversedBy: 'reservations')]
    #[ORM\JoinColumn(name: 'id_client_id', referencedColumnName: 'id_client')]
    private ?Client $id_client_id = null;

    #[ORM\ManyToOne(inversedBy: 'reservations')]
    #[ORM\JoinColumn(name: 'id_conducteur_id', referencedColumnName: 'id_conducteur')]
    private ?Conducteur $id_conducteur_id = null;

    #[ORM\OneToOne(inversedBy: 'reservation', cascade: ['persist', 'remove'])]
    #[ORM\JoinColumn(nullable: false)]
    private ?Offre $id_offre = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNbPlace(): ?int
    {
        return $this->nb_place;
    }

    public function setNbPlace(int $nb_place): self
    {
        $this->nb_place = $nb_place;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getPointDeDepart(): ?string
    {
        return $this->point_de_depart;
    }

    public function setPointDeDepart(string $point_de_depart): self
    {
        $this->point_de_depart = $point_de_depart;

        return $this;
    }

    public function getPointArrive(): ?string
    {
        return $this->point_arrive;
    }

    public function setPointArrive(string $point_arrive): self
    {
        $this->point_arrive = $point_arrive;

        return $this;
    }

    public function getIdClient(): ?Client
    {
        return $this->id_client_id;
    }

    public function setIdClient(?Client $id_client): self
    {
        $this->id_client_id = $id_client;

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

    public function getIdOffre(): ?Offre
    {
        return $this->id_offre;
    }

    public function setIdOffre(Offre $id_offre): self
    {
        $this->id_offre = $id_offre;

        return $this;
    }

    public function getIdClientId(): ?Client
    {
        return $this->id_client_id;
    }

    public function setIdClientId(?Client $id_client_id): self
    {
        $this->id_client_id = $id_client_id;

        return $this;
    }

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
