<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Conducteur
 *
 * @ORM\Table(name="conducteur")
 * @ORM\Entity
 */
class Conducteur
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_conducteur", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idConducteur;

    /**
     * @var int
     *
     * @ORM\Column(name="cin_conducteur", type="integer", nullable=false)
     */
    private $cinConducteur;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_conducteur", type="string", length=50, nullable=false)
     */
    private $nomConducteur;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom_conducteur", type="string", length=50, nullable=false)
     */
    private $prenomConducteur;

    /**
     * @var string
     *
     * @ORM\Column(name="ville_conducteur", type="string", length=50, nullable=false)
     */
    private $villeConducteur;

    /**
     * @var int
     *
     * @ORM\Column(name="telephone_conducteur", type="integer", nullable=false)
     */
    private $telephoneConducteur;

    /**
     * @var string
     *
     * @ORM\Column(name="email_conducteur", type="string", length=200, nullable=false)
     */
    private $emailConducteur;

    /**
     * @var string
     *
     * @ORM\Column(name="mdp_conducteur", type="string", length=50, nullable=false)
     */
    private $mdpConducteur;

    /**
     * @var string|null
     *
     * @ORM\Column(name="type_de_permis", type="string", length=11, nullable=true)
     */
    private $typeDePermis;

    /**
     * @var string|null
     *
     * @ORM\Column(name="image_conducteur", type="string", length=255, nullable=true)
     */
    private $imageConducteur;

    public function getIdConducteur(): ?int
    {
        return $this->idConducteur;
    }

    public function getCinConducteur(): ?int
    {
        return $this->cinConducteur;
    }

    public function setCinConducteur(int $cinConducteur): self
    {
        $this->cinConducteur = $cinConducteur;

        return $this;
    }

    public function getNomConducteur(): ?string
    {
        return $this->nomConducteur;
    }

    public function setNomConducteur(string $nomConducteur): self
    {
        $this->nomConducteur = $nomConducteur;

        return $this;
    }

    public function getPrenomConducteur(): ?string
    {
        return $this->prenomConducteur;
    }

    public function setPrenomConducteur(string $prenomConducteur): self
    {
        $this->prenomConducteur = $prenomConducteur;

        return $this;
    }

    public function getVilleConducteur(): ?string
    {
        return $this->villeConducteur;
    }

    public function setVilleConducteur(string $villeConducteur): self
    {
        $this->villeConducteur = $villeConducteur;

        return $this;
    }

    public function getTelephoneConducteur(): ?int
    {
        return $this->telephoneConducteur;
    }

    public function setTelephoneConducteur(int $telephoneConducteur): self
    {
        $this->telephoneConducteur = $telephoneConducteur;

        return $this;
    }

    public function getEmailConducteur(): ?string
    {
        return $this->emailConducteur;
    }

    public function setEmailConducteur(string $emailConducteur): self
    {
        $this->emailConducteur = $emailConducteur;

        return $this;
    }

    public function getMdpConducteur(): ?string
    {
        return $this->mdpConducteur;
    }

    public function setMdpConducteur(string $mdpConducteur): self
    {
        $this->mdpConducteur = $mdpConducteur;

        return $this;
    }

    public function getTypeDePermis(): ?string
    {
        return $this->typeDePermis;
    }

    public function setTypeDePermis(?string $typeDePermis): self
    {
        $this->typeDePermis = $typeDePermis;

        return $this;
    }

    public function getImageConducteur(): ?string
    {
        return $this->imageConducteur;
    }

    public function setImageConducteur(?string $imageConducteur): self
    {
        $this->imageConducteur = $imageConducteur;

        return $this;
    }


}
