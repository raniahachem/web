<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;
use DateTime; 
 
/**
 * Reservation
 *
 * @ORM\Table(name="reservation")
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\ReservationRepository")
 */
class Reservation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_reservation", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idReservation;

    /**
     * @var int
     *
     * @ORM\Column(name="nb_place", type="integer", nullable=false)
     */
    #[Assert\NotBlank(message:"Veuillez remplir ce champ ")]
    private $nbPlace;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     */
    #[Assert\NotBlank(message:"Veuillez remplir ce champ ")]
    private $date;

    /**
     * @var int
     *
     * @ORM\Column(name="id_client", type="integer", nullable=false)
     */
    #[Assert\NotBlank(message:"Veuillez remplir ce champ ")]
    #[Assert\NotBlank(message:"vous devez choisir un id qui existe déjà dans la base  ")]

    private $idClient;

    /**
     * @var string
     *
     * @ORM\Column(name="point_de_depart", type="string", length=255, nullable=false)
     */
    #[Assert\NotBlank(message:"Veuillez remplir ce champ ")]
    private $pointDeDepart;

    /**
     * @var string
     *
     * @ORM\Column(name="point_arrive", type="string", length=255, nullable=false)
     */
    #[Assert\NotBlank(message:"Veuillez remplir ce champ ")]
    private $pointArrive;

    /**
     * @var int
     *
     * @ORM\Column(name="id_conducteur", type="integer", nullable=false)
     */
    #[Assert\NotBlank(message:"Veuillez remplir ce champ ")]
    #[Assert\NotBlank(message:"vous devez choisir un id qui existe déjà dans la base  ")]
    private $idConducteur;

    
     

    /**
     * @var int
     *
     * @ORM\Column(name="id_offre", type="integer", nullable=false)
     */
    #[Assert\NotBlank(message:"Veuillez remplir ce champ ")]
    #[ORM\OneToOne(inversedBy: 'reservation')]
    private $idOffre;

    
    public function getIdReservation(): ?int
    {
        return $this->idReservation;
    }

    public function getNbPlace(): ?int
    {
        return $this->nbPlace;
    }

    public function setNbPlace(int $nbPlace): self
    {
        $this->nbPlace = $nbPlace;

        return $this;
    }

     public function getDate(): ?\DateTimeInterface
     {
         return $this->date;
     }

    //public function getDate(): ?DateTimeInterface
    //{    
        //$dateTime = DateTime::createFromFormat('Y-m-d H:i:s', $this->date);
        //$this-> $dateTime instanceof DateTimeInterface ? $dateTime : null;
         
        
        
   // }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getIdClient(): ?int
    {
        return $this->idClient;
    }

    public function setIdClient(int $idClient): self
    {
        $this->idClient = $idClient;

        return $this;
    }

    public function getPointDeDepart(): ?string
    {
        return $this->pointDeDepart;
    }

    public function setPointDeDepart(string $pointDeDepart): self
    {
        $this->pointDeDepart = $pointDeDepart;

        return $this;
    }

    public function getPointArrive(): ?string
    {
        return $this->pointArrive;
    }

    public function setPointArrive(string $pointArrive): self
    {
        $this->pointArrive = $pointArrive;

        return $this;
    }

    public function getIdConducteur(): ?int
    {
        return $this->idConducteur;
    }

    public function setIdConducteur(int $idConducteur): self
    {
        $this->idConducteur = $idConducteur;

        return $this;
    }

    public function getIdOffre(): ?int
    {
        return $this->idOffre;
    }

    public function setIdOffre(int $idOffre): self
    {
        $this->idOffre = $idOffre;

        return $this;
    }


}
