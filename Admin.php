<?php

namespace App\Entity;

use App\Repository\AdminRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: AdminRepository::class)]
class Admin
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id_admin = null;

    #[ORM\Column(length: 255)]
    private ?string $email_ad = null;

    #[ORM\Column(length: 255)]
    private ?string $mdp_ad = null;

    public function getIdAdmin(): ?int
    {
        return $this->id_admin;
    }

    public function getEmailAd(): ?string
    {
        return $this->email_ad;
    }

    public function setEmailAd(string $email_ad): self
    {
        $this->email_ad = $email_ad;

        return $this;
    }

    public function getMdpAd(): ?string
    {
        return $this->mdp_ad;
    }

    public function setMdpAd(string $mdp_ad): self
    {
        $this->mdp_ad = $mdp_ad;

        return $this;
    }
}
