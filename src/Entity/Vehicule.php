<?php

namespace App\Entity;

use App\Repository\VehiculeRepository;
use App\Entity\Conducteur;

use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: VehiculeRepository::class)]
class Vehicule
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(length: 255)]
    private ?string $id = null;

    #[ORM\Column(length: 255)]
    private ?string $typeDyVehicule = null;

    #[ORM\Column(length: 255)]
    private ?string $marque = null;

    #[ORM\Column]
    private ?int $etat = null;

    #[ORM\Column]
    private ?int $pdfFile = null;

    #[ORM\Column]
    private ?int $kilometrage = null;

    #[ORM\Column(length: 255)]
    private ?string $imagev = null;

    #[ORM\ManyToOne(inversedBy: 'vehicules')]
    #[ORM\JoinColumn(name: 'fournisseurid', referencedColumnName: 'id')]
    private ?Fournisseur $fournisseurid = null;

    #[ORM\ManyToOne(inversedBy: 'vehicules')]
    #[ORM\JoinColumn(name: 'idconducteur', referencedColumnName: 'id_conducteur')]
    private ?Conducteur $idconducteur = null;

    public function getId(): ?string
    {
        return $this->id;
    }

    public function getTypeDyVehicule(): ?string
    {
        return $this->typeDyVehicule;
    }

    public function setTypeDyVehicule(string $typeDyVehicule): self
    {
        $this->typeDyVehicule = $typeDyVehicule;

        return $this;
    }

    public function getMarque(): ?string
    {
        return $this->marque;
    }

    public function setMarque(string $marque): self
    {
        $this->marque = $marque;

        return $this;
    }

    public function getEtat(): ?int
    {
        return $this->etat;
    }

    public function setEtat(int $etat): self
    {
        $this->etat = $etat;

        return $this;
    }

    public function getPdfFile(): ?int
    {
        return $this->pdfFile;
    }

    public function setPdfFile(int $pdfFile): self
    {
        $this->pdfFile = $pdfFile;

        return $this;
    }

    public function getKilometrage(): ?int
    {
        return $this->kilometrage;
    }

    public function setKilometrage(int $kilometrage): self
    {
        $this->kilometrage = $kilometrage;

        return $this;
    }

    public function getImagev(): ?string
    {
        return $this->imagev;
    }

    public function setImagev(string $imagev): self
    {
        $this->imagev = $imagev;

        return $this;
    }

    public function getFournisseurid(): ?Fournisseur
    {
        return $this->fournisseurid;
    }

    public function setFournisseurid(?Fournisseur $fournisseurid): self
    {
        $this->fournisseurid = $fournisseurid;

        return $this;
    }

    public function getIdconducteur(): ?Conducteur
    {
        return $this->idconducteur;
    }

    public function setIdconducteur(?Conducteur $idconducteur): self
    {
        $this->idconducteur = $idconducteur;

        return $this;
    }
}
