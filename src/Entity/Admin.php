<?php

namespace App\Entity;

use App\Repository\AdminRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
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

    #[ORM\OneToMany(mappedBy: 'id_admin_id', targetEntity: Message::class)]
    private Collection $messages;

    public function __construct()
    {
        $this->messages = new ArrayCollection();
    }

    public function getId(): ?int
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

    /**
     * @return Collection<int, Message>
     */
    public function getMessages(): Collection
    {
        return $this->messages;
    }

    public function addMessage(Message $message): self
    {
        if (!$this->messages->contains($message)) {
            $this->messages->add($message);
            $message->setIdAdmin($this);
        }

        return $this;
    }

    public function removeMessage(Message $message): self
    {
        if ($this->messages->removeElement($message)) {
            // set the owning side to null (unless already changed)
            if ($message->getIdAdmin() === $this) {
                $message->setIdAdmin(null);
            }
        }

        return $this;
    }

    public function __toString()
    {
        return $this->getEmailAd();
    }

    public function getIdAdmin(): ?int
    {
        return $this->id_admin;
    }
}
