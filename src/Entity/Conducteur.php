<?php

namespace App\Entity;
use App\Repository\AvisRepository;
use App\Repository\ConducteurRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use App\Entity\Vehicule;


use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ConducteurRepository::class)]
class Conducteur
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id_conducteur = null;

    #[ORM\Column]
    private ?int $cin_conducteur = null;

    #[ORM\Column(length: 255)]
    private ?string $nom_conducteur = null;

    #[ORM\Column(length: 255)]
    private ?string $prenom_conducteur = null;

    #[ORM\Column(length: 255)]
    private ?string $ville_conducteur = null;

    #[ORM\Column]
    private ?int $telephone_conducteur = null;

    #[ORM\Column(length: 255)]
    private ?string $email_conducteur = null;

    #[ORM\Column(length: 255)]
    private ?string $mdp_conducteur = null;

    #[ORM\Column(length: 255)]
    private ?string $type_de_permis = null;

    #[ORM\Column(length: 255)]
    private ?string $image_conducteur = null;


  /*   #[ORM\OneToMany(mappedBy: 'id_conducteur_id', targetEntity: Offre::class)]
    private Collection $offres;

    #[ORM\OneToMany(mappedBy: 'id_conducteur_id', targetEntity: Reservation::class)]
    private Collection $reservations; */
    #[ORM\OneToMany(mappedBy: 'id_conducteur_id', targetEntity: Offre::class)]
    private Collection $offres;

    #[ORM\OneToMany(mappedBy: 'id_conducteur_id', targetEntity: Vehicule::class)]
    private Collection $vehicules;

    #[ORM\OneToMany(mappedBy: 'id_conducteur_id', targetEntity: Reservation::class)]
    private Collection $reservations;

    public function getVehicules(): Collection
    {
        return $this->vehicules;
    }

    public function addVehicule(Vehicule $vehicule): self
    {
        if (!$this->vehicules->contains($vehicule)) {
            $this->vehicules[] = $vehicule;
            $vehicule->setIdconducteur($this);
        }

        return $this;
    }

    public function removeVehicule(Vehicule $vehicule): self
    {
        if ($this->vehicules->removeElement($vehicule)) {
            // set the owning side to null (unless already changed)
            if ($vehicule->getIdconducteur() === $this) {
                $vehicule->setIdconducteur(null);
            }
        }

        return $this;
    }


    public function __construct()
    {
        $this->offres = new ArrayCollection();
        $this->reservations = new ArrayCollection();
        $this->vehicules = new ArrayCollection();

    }

    public function getIdConducteur(): ?int
    {
        return $this->id_conducteur;
    }

    public function setIdConducteur(int $id_conducteur): self
    {
        $this->id_conducteur = $id_conducteur;

        return $this;
    }

    public function getCinConducteur(): ?int
    {
        return $this->cin_conducteur;
    }

    public function setCinConducteur(int $cin_conducteur): self
    {
        $this->cin_conducteur = $cin_conducteur;

        return $this;
    }

    public function getNomConducteur(): ?string
    {
        return $this->nom_conducteur;
    }

    public function setNomConducteur(string $nom_conducteur): self
    {
        $this->nom_conducteur = $nom_conducteur;

        return $this;
    }

    public function getPrenomConducteur(): ?string
    {
        return $this->prenom_conducteur;
    }

    public function setPrenomConducteur(string $prenom_conducteur): self
    {
        $this->prenom_conducteur = $prenom_conducteur;

        return $this;
    }

    public function getVilleConducteur(): ?string
    {
        return $this->ville_conducteur;
    }

    public function setVilleConducteur(string $ville_conducteur): self
    {
        $this->ville_conducteur = $ville_conducteur;

        return $this;
    }

    public function getTelephoneConducteur(): ?int
    {
        return $this->telephone_conducteur;
    }

    public function setTelephoneConducteur(int $telephone_conducteur): self
    {
        $this->telephone_conducteur = $telephone_conducteur;

        return $this;
    }

    public function getEmailConducteur(): ?string
    {
        return $this->email_conducteur;
    }

    public function setEmailConducteur(string $email_conducteur): self
    {
        $this->email_conducteur = $email_conducteur;

        return $this;
    }

    public function getMdpConducteur(): ?string
    {
        return $this->mdp_conducteur;
    }

    public function setMdpConducteur(string $mdp_conducteur): self
    {
        $this->mdp_conducteur = $mdp_conducteur;

        return $this;
    }

    public function getTypeDePermis(): ?string
    {
        return $this->type_de_permis;
    }

    public function setTypeDePermis(string $type_de_permis): self
    {
        $this->type_de_permis = $type_de_permis;

        return $this;
    }

    public function getImageConducteur(): ?string
    {
        return $this->image_conducteur;
    }

    public function setImageConducteur(string $image_conducteur): self
    {
        $this->image_conducteur = $image_conducteur;

        return $this;
    }

    

    
    /**
     * @return Collection<int, Offre>
     */
    public function getOffres(): Collection
    {
        return $this->offres;
    }
    

    public function addOffre(Offre $offre): self
    {
        if (!$this->offres->contains($offre)) {
            $this->offres->add($offre);
            $offre->setIdConducteur($this);
        }

        return $this;
    }

    public function removeOffre(Offre $offre): self
    {
        if ($this->offres->removeElement($offre)) {
            // set the owning side to null (unless already changed)
            if ($offre->getIdConducteur() === $this) {
                $offre->setIdConducteur(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection<int, Reservation>
     */
    public function getReservations(): Collection
    {
        return $this->reservations;
    }

    public function addReservation(Reservation $reservation): self
    {
        if (!$this->reservations->contains($reservation)) {
            $this->reservations->add($reservation);
            $reservation->setIdConducteur($this);
        }

        return $this;
    }

    public function removeReservation(Reservation $reservation): self
    {
        if ($this->reservations->removeElement($reservation)) {
            // set the owning side to null (unless already changed)
            if ($reservation->getIdConducteur() === $this) {
                $reservation->setIdConducteur(null);
            }
        }

        return $this;
    }
}
