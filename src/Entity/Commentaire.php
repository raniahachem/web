<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use App\Repository\CommentaireRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: CommentaireRepository::class)]
class Commentaire
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;


    #[ORM\Column(length: 255)]
    #[Assert\NotBlank(message:"Veuillez remplir ce champ ")]
    private ?string $contenu = null;

    #[ORM\Column(type: Types::DATETIME_MUTABLE)]
    private ?\DateTimeInterface $date = null;

    #[ORM\Column]
    private ?int $id_conducteur = null;

    #[ORM\Column]
    private ?int $id_client = null;

    #[ORM\Column]
    private ?int $id_avis = null;

 

    public function getId(): ?int
    {
        return $this->id;
    }


    public function getContenu(): ?string
    {
        return $this->contenu;
    }

    public function setContenu(string $contenu): self
    {
        $this->contenu = $contenu;

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

    public function getIdConducteur(): ?int
    {
        return $this->id_conducteur;
    }

    public function setIdConducteur(int $id_conducteur): self
    {
        $this->id_conducteur = $id_conducteur;

        return $this;
    }

    public function getIdClient(): ?int
    {
        return $this->id_client;
    }

    public function setIdClient(int $id_client): self
    {
        $this->id_client = $id_client;

        return $this;
    }


    public function getIdAvis(): ?int
    {
        return $this->id_avis;
    }

    public function setIdAvis(int $id_avis): self
    {
        $this->id_avis = $id_avis;

        return $this;
    }
}
