<?php

namespace App\Entity;

use App\Repository\ClientRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ClientRepository::class)]
class Client
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private $id_client = null;


    #[ORM\Column]
    private  ?int  $cin_client;

    #[ORM\Column(length: 255)]
    private ?string $nom_client;

    #[ORM\Column(length: 255)]
    private  ?string  $prenom_client;

    #[ORM\Column(length: 255)]
    private  ?string  $ville_client;


    #[ORM\Column]
    private  ?int $telephone_client;

    #[ORM\Column(length: 255)]
    private  ?string  $email_client;

    #[ORM\Column(length: 255)]
    private  ?string  $mdp_client;

    #[ORM\Column(length: 255)]
    private  ?string  $role;



    #[ORM\ManyToMany(mappedBy: 'id_client', targetEntity: Commentaire::class)]
    private Collection $commentaire;



    public function getIdClient(): ?int
    {
        return $this->id_client;
    }

    public function getCinClient(): ?int
    {
        return $this->cin_client;
    }

    public function setCinClient(?int $cin_client): void
    {
        $this->cin_client = $cin_client;
    }

    public function getNomClient(): ?string
    {
        return $this->nom_client;
    }

    public function setNomClient(?string $nom_client): void
    {
        $this->nom_client = $nom_client;
    }

    public function getPrenomClient(): ?string
    {
        return $this->prenom_client;
    }

    public function setPrenomClient(?string $prenom_client): void
    {
        $this->prenom_client = $prenom_client;
    }

    public function getVilleClient(): ?string
    {
        return $this->ville_client;
    }

    public function setVilleClient(?string $ville_client): void
    {
        $this->ville_client = $ville_client;
    }

    public function getTelephoneClient(): ?int
    {
        return $this->telephone_client;
    }

    public function setTelephoneClient(?int $telephone_client): void
    {
        $this->telephone_client = $telephone_client;
    }

    public function getEmailClient(): ?string
    {
        return $this->email_client;
    }

    public function setEmailClient(?string $email_client): void
    {
        $this->email_client = $email_client;
    }

    public function getMdpClient(): ?string
    {
        return $this->mdp_client;
    }

    public function setMdpClient(?string $mdp_client): void
    {
        $this->mdp_client = $mdp_client;
    }

    public function getRole(): ?string
    {
        return $this->role;
    }

    public function setRole(?string $role): void
    {
        $this->role = $role;
    }

    /**
     * @return Collection<int, Commentaire>
     */
    public function getCommentaire(): Collection
    {
        return $this->commentaire;
    }

    public function addCommentaire(Commentaire $commentaire): self
    {
        if (!$this->commentaire->contains($commentaire)) {
            $this->commentaire->add($commentaire);
            $commentaire->addIdClient($this);
        }

        return $this;
    }

    public function removeCommentaire(Commentaire $commentaire): self
    {
        if ($this->commentaire->removeElement($commentaire)) {
            $commentaire->removeIdClient($this);
        }

        return $this;
    }
}
