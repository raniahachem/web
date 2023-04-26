<?php

namespace App\Entity;

use App\Repository\ConducteurRepository;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Security\Core\User\PasswordAuthenticatedUserInterface;
use Symfony\Component\Security\Core\User\UserInterface;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ConducteurRepository::class)]
#[UniqueEntity(fields: ['email_conducteur'], message: 'There is already an account with this email_conducteur')]
class Conducteur implements UserInterface, PasswordAuthenticatedUserInterface
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id_conducteur = null;

    #[ORM\Column]
    private ?int $cin_conducteur = null;

    #[ORM\Column(length: 255)]
    private ?string $nom_conducteur = null;

    #[ORM\Column(length: 255)]
    private ?string $prenom_conducteur = null;

    #[ORM\Column]
    private ?int $telephone_conducteur = null;

    #[ORM\Column(length: 255)]
    private ?string $email_conducteur = null;

    #[ORM\Column(length: 255)]
    private ?string $ville_conducteur = null;

    #[ORM\Column(length: 255)]
    private ?string $mdp_conducteur = null;

    #[ORM\Column(length: 255)]
    private ?string $type_de_permis = null;

    #[ORM\Column(length: 255)]
    private ?string $image_conducteur = null;
    #[ORM\Column(type: 'json')]
     private $roles = [];

    #[ORM\Column(type: 'boolean')]
    private $isVerified = false;

    public function getIdConducteur(): ?int
    {
        return $this->id_conducteur;
    }

    public function getCinConducteur(): ?int
    {
        return $this->cin_conducteur;
    }

    public function setCinConducteur(int $cin_conducteur): self
    {
        $this->cin_conducteur = $cin_conducteur;

        return $this;
    }

    public function getNomConducteur(): ?string
    {
        return $this->nom_conducteur;
    }

    public function setNomConducteur(string $nom_conducteur): self
    {
        $this->nom_conducteur = $nom_conducteur;

        return $this;
    }

    public function getPrenomConducteur(): ?string
    {
        return $this->prenom_conducteur;
    }

    public function setPrenomConducteur(string $prenom_conducteur): self
    {
        $this->prenom_conducteur = $prenom_conducteur;

        return $this;
    }

    public function getTelephoneConducteur(): ?int
    {
        return $this->telephone_conducteur;
    }

    public function setTelephoneConducteur(int $telephone_conducteur): self
    {
        $this->telephone_conducteur = $telephone_conducteur;

        return $this;
    }

    public function getEmailConducteur(): ?string
    {
        return $this->email_conducteur;
    }

    public function setEmailConducteur(string $email_conducteur): self
    {
        $this->email_conducteur = $email_conducteur;

        return $this;
    }

    public function getVilleConducteur(): ?string
    {
        return $this->ville_conducteur;
    }

    public function setVilleConducteur(string $ville_conducteur): self
    {
        $this->ville_conducteur = $ville_conducteur;

        return $this;
    }

    public function getMdpConducteur(): ?string
    {
        return $this->mdp_conducteur;
    }

    public function setMdpConducteur(string $mdp_conducteur): self
    {
        $this->mdp_conducteur = $mdp_conducteur;

        return $this;
    }

    public function getTypeDePermis(): ?string
    {
        return $this->type_de_permis;
    }

    public function setTypeDePermis(string $type_de_permis): self
    {
        $this->type_de_permis = $type_de_permis;

        return $this;
    }

    public function getImageConducteur(): ?string
    {
        return $this->image_conducteur;
    }

    public function setImageConducteur(string $image_conducteur): self
    {
        $this->image_conducteur = $image_conducteur;

        return $this;
    }
    
    /**
     * The public representation of the user (e.g. a username, an email address, etc.)
     *
     * @see UserInterface
     */
    public function getUserIdentifier(): string
    {
        return (string) $this->email_conducteur;
    }
    public function getUsername(): string
    {
        return (string) $this->email_conducteur;
    }

    /**
     * @see UserInterface
     */
    public function getRoles(): array
    {
        $roles = $this->roles;
        // guarantee every user at least has ROLE_USER
        $roles[] = 'ROLE_USER';

        return array_unique($roles);
    }

    public function setRoles(array $roles): self
    {
        $this->roles = $roles;

        return $this;
    }

    /**
     * @see PasswordAuthenticatedUserInterface
     */
    public function getPassword(): string
    {
        return $this->mdp_conducteur;
    }

    public function setPassword(string $password): self
    {
        $this->mdp_conducteur = $password;

        return $this;
    }

    /**
     * Returning a salt is only needed if you are not using a modern
     * hashing algorithm (e.g. bcrypt or sodium) in your security.yaml.
     *
     * @see UserInterface
     */
    public function getSalt(): ?string
    {
        return null;
    }

    /**
     * @see UserInterface
     */
    public function eraseCredentials()
    {
        // If you store any temporary, sensitive data on the user, clear it here
        // $this->plainPassword = null;
    }

    public function isVerified(): bool
    {
        return $this->isVerified;
    }

    public function setIsVerified(bool $isVerified): self
    {
        $this->isVerified = $isVerified;

        return $this;
    }
}
