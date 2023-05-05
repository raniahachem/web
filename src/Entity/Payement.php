<?php

namespace App\Entity;

use App\Repository\PayementRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: PayementRepository::class)]
class Payement
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name: "paymentId", type: "integer")]
    private ?int $id = null;

    #[ORM\Column(name: "client", type: "integer")]
    private ?int $client = null;

    #[ORM\Column(length: 255)]
    private ?string $modePayment = null;

    #[ORM\Column(name: "prixCourse")]
    private ?float $prix = null;

    #[ORM\Column(type: Types::DATETIME_MUTABLE)]
    private ?\DateTimeInterface $paymentDate = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getClient(): ?int
    {
        return $this->client;
    }

    public function setClient(?int $client): self
    {
        $this->client = $client;

        return $this;
    }

    public function getModePayment(): ?string
    {
        return $this->modePayment;
    }

    public function setModePayment(string $modePayment): self
    {
        $this->modePayment = $modePayment;

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

    public function getPaymentDate(): ?\DateTimeInterface
    {
        return $this->paymentDate;
    }

    public function setPaymentDate(\DateTimeInterface $paymentDate): self
    {
        $this->paymentDate = $paymentDate;

        return $this;
    }
}
