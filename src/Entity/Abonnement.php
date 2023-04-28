<?php

namespace App\Entity;

use App\Repository\AbonnementRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: AbonnementRepository::class)]
class Abonnement
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    private ?string $typeAb = null;

    #[ORM\Column]
    private ?float $prixAb = null;

    #[ORM\Column(length: 255)]
    private ?string $modePaiementAb = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTypeAb(): ?string
    {
        return $this->typeAb;
    }

    public function setTypeAb(string $typeAb): self
    {
        $this->typeAb = $typeAb;

        return $this;
    }

    public function getPrixAb(): ?float
    {
        return $this->prixAb;
    }

    public function setPrixAb(float $prixAb): self
    {
        $this->prixAb = $prixAb;

        return $this;
    }

    public function getModePaiementAb(): ?string
    {
        return $this->modePaiementAb;
    }

    public function setModePaiementAb(string $modePaiementAb): self
    {
        $this->modePaiementAb = $modePaiementAb;

        return $this;
    }
}
