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


    #[ORM\OneToMany(mappedBy: 'id_client_id', targetEntity: Reclamation::class)]
    private Collection $reclamations;

    #[ORM\OneToMany(mappedBy: 'id_client', targetEntity: Reservation::class)]
    private Collection $reservations;

    
    
    public function __construct()
    {
        $this->reclamations = new ArrayCollection();
        $this->reservations = new ArrayCollection();
        $this->commentaire = new ArrayCollection();
    }


    

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



/**
     * @return Collection<int, Reclamation>
     */
    public function getReclamations(): Collection
    {
        return $this->reclamations;
    }

    public function addReclamation(Reclamation $reclamation): self
    {
        if (!$this->reclamations->contains($reclamation)) {
            $this->reclamations->add($reclamation);
            $reclamation->setIdClient($this);
        }

        return $this;
    }

    public function removeReclamation(Reclamation $reclamation): self
    {
        if ($this->reclamations->removeElement($reclamation)) {
            // set the owning side to null (unless already changed)
            if ($reclamation->getIdClient() === $this) {
                $reclamation->setIdClient(null);
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
            $reservation->setIdClient($this);
        }

        return $this;
    }

    public function removeReservation(Reservation $reservation): self
    {
        if ($this->reservations->removeElement($reservation)) {
            // set the owning side to null (unless already changed)
            if ($reservation->getIdClient() === $this) {
                $reservation->setIdClient(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection<int, Payement>
     */
    public function getModePayment(): Collection
    {
        return $this->modePayment;
    }

    public function addModePayment(Payement $modePayment): self
    {
        if (!$this->modePayment->contains($modePayment)) {
            $this->modePayment->add($modePayment);
            $modePayment->setClient($this);
        }

        return $this;
    }

    public function removeModePayment(Payement $modePayment): self
    {
        if ($this->modePayment->removeElement($modePayment)) {
            // set the owning side to null (unless already changed)
            if ($modePayment->getClient() === $this) {
                $modePayment->setClient(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection<int, Payement>
     */
    public function getPaiements(): Collection
    {
        return $this->paiements;
    }

    public function addPaiement(Payement $paiement): self
    {
        if (!$this->paiements->contains($paiement)) {
            $this->paiements->add($paiement);
            $paiement->setClient($this);
        }

        return $this;
    }

    public function removePaiement(Payement $paiement): self
    {
        if ($this->paiements->removeElement($paiement)) {
            // set the owning side to null (unless already changed)
            if ($paiement->getClient() === $this) {
                $paiement->setClient(null);
            }
        }

        return $this;
    }
}
