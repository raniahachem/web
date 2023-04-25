<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use App\Repository\OffreRepository;
use Doctrine\Common\Collections\ArrayCollection;

/**
 * Offre
 *
 * @ORM\Table(name="offre")
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\OffreRepository")
 */
 class Offre
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_offre", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idOffre;




    
    /**
     * @var int
     *
     * @ORM\Column(name="id_conducteur", type="integer", nullable=false)
     * @Assert\GreaterThanOrEqual(
     *      value = 0,
     *      message = "Il faut utilisé un id qui existe déjà dans la base de l'admin"
     * )
     */
    #[Assert\NotBlank(message:"Veuillez remplir ce champ ")]
    private $idConducteur;
    

    /**
     * @var string
     *
     * @ORM\Column(name="Destination", type="string", length=255, nullable=false)
     * @Assert\Length(
     *      min = 2,
     *      minMessage = "la destination doit être au moins entre 1-254",
     * )
     */
    #[Assert\NotBlank(message:"Veuillez remplir ce champ ")]
    private $destination;

    /**
     * @var string
     *
     * @ORM\Column(name="pt_depart", type="string", length=255, nullable=false)
     */
    #[Assert\NotBlank(message:"Veuillez remplir ce champ ")]
    private $ptDepart;

    /**
     * @var float
     *
     * @ORM\Column(name="Prix", type="float", precision=10, scale=0, nullable=false)
     * @Assert\NotEqualTo(
     *      value = 0,
     *      message = "Le prix d'une offre ne doit pas etre égale à 0"
     * )
     */
    #[Assert\NotBlank(message:"Veuillez remplir ce champ  ")]
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="Type_vehicule", type="string", length=255, nullable=false)
     */
    #[Assert\NotBlank(message:"Veuillez remplir ce champ ")]
    private $typeVehicule;


    #[ORM\OneToOne(mappedBy: 'idOffre', targetEntity: Reservation::class)]
    private Collection $reservations;

     


    public function getIdOffre(): ?int
    {
        return $this->idOffre;
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

    public function getDestination(): ?string
    {
        return $this->destination;
    }

    public function setDestination(string $destination): self
    {
        $this->destination = $destination;

        return $this;
    }

    public function getPtDepart(): ?string
    {
        return $this->ptDepart;
    }

    public function setPtDepart(string $ptDepart): self
    {
        $this->ptDepart = $ptDepart;

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
        return $this->typeVehicule;
    }

    public function setTypeVehicule(string $typeVehicule): self
    {
        $this->typeVehicule = $typeVehicule;

        return $this;
    }


}
