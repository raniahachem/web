<?php

namespace App\Entity;

use App\Repository\FournisseurRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: FournisseurRepository::class)]
class Fournisseur
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    private ?string $nomfournisseur = null;

    #[ORM\Column(length: 255)]
    private ?string $addressfournisseur = null;

    #[ORM\Column]
    private ?int $numfournisseur = null;

    #[ORM\Column(length: 255)]
    private ?string $mailfournisseur = null;

    #[ORM\OneToMany(mappedBy: 'fournisseurid', targetEntity: Vehicule::class)]
    private Collection $vehicules;

    public function __construct()
    {
        $this->vehicules = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomfournisseur(): ?string
    {
        return $this->nomfournisseur;
    }

    public function setNomfournisseur(string $nomfournisseur): self
    {
        $this->nomfournisseur = $nomfournisseur;

        return $this;
    }

    public function getAddressfournisseur(): ?string
    {
        return $this->addressfournisseur;
    }

    public function setAddressfournisseur(string $addressfournisseur): self
    {
        $this->addressfournisseur = $addressfournisseur;

        return $this;
    }

    public function getNumfournisseur(): ?int
    {
        return $this->numfournisseur;
    }

    public function setNumfournisseur(int $numfournisseur): self
    {
        $this->numfournisseur = $numfournisseur;

        return $this;
    }

    public function getMailfournisseur(): ?string
    {
        return $this->mailfournisseur;
    }

    public function setMailfournisseur(string $mailfournisseur): self
    {
        $this->mailfournisseur = $mailfournisseur;

        return $this;
    }

    /**
     * @return Collection<int, Vehicule>
     */
    public function getVehicules(): Collection
    {
        return $this->vehicules;
    }

    public function addVehicule(Vehicule $vehicule): self
    {
        if (!$this->vehicules->contains($vehicule)) {
            $this->vehicules->add($vehicule);
            $vehicule->setFournisseurid($this);
        }

        return $this;
    }

    public function removeVehicule(Vehicule $vehicule): self
    {
        if ($this->vehicules->removeElement($vehicule)) {
            // set the owning side to null (unless already changed)
            if ($vehicule->getFournisseurid() === $this) {
                $vehicule->setFournisseurid(null);
            }
        }

        return $this;
    }
}
