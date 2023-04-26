<?php

namespace App\Entity;

use App\Repository\ClientRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ClientRepository::class)]
class Client
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id_client = null;

    #[ORM\Column(length: 255)]
    private ?string $nom_client = null;

    #[ORM\Column(length: 255)]
    private ?string $prenom_client = null;

    #[ORM\Column]
    private ?int $cin_client = null;

    #[ORM\Column(length: 255)]
    private ?string $ville_client = null;

    #[ORM\Column]
    private ?int $telephone_client = null;

    #[ORM\Column(length: 255)]
    private ?string $email_client = null;

    #[ORM\Column(length: 255)]
    private ?string $mdp_client = null;

    public function getIdClient(): ?int
    {
        return $this->id_client;
    }

    public function getNomClient(): ?string
    {
        return $this->nom_client;
    }

    public function setNomClient(string $nom_client): self
    {
        $this->nom_client = $nom_client;

        return $this;
    }

    public function getPrenomClient(): ?string
    {
        return $this->prenom_client;
    }

    public function setPrenomClient(string $prenom_client): self
    {
        $this->prenom_client = $prenom_client;

        return $this;
    }

    public function getCinClient(): ?int
    {
        return $this->cin_client;
    }

    public function setCinClient(int $cin_client): self
    {
        $this->cin_client = $cin_client;

        return $this;
    }

    public function getVilleClient(): ?string
    {
        return $this->ville_client;
    }

    public function setVilleClient(string $ville_client): self
    {
        $this->ville_client = $ville_client;

        return $this;
    }

    public function getTelephoneClient(): ?int
    {
        return $this->telephone_client;
    }

    public function setTelephoneClient(int $telephone_client): self
    {
        $this->telephone_client = $telephone_client;

        return $this;
    }

    public function getEmailClient(): ?string
    {
        return $this->email_client;
    }

    public function setEmailClient(string $email_client): self
    {
        $this->email_client = $email_client;

        return $this;
    }

    public function getMdpClient(): ?string
    {
        return $this->mdp_client;
    }

    public function setMdpClient(string $mdp_client): self
    {
        $this->mdp_client = $mdp_client;

        return $this;
    }
}



