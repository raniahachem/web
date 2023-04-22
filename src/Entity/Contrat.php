<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;



/**
 * Contrat
 *
 * @ORM\Table(name="contrat")
 * @ORM\Entity
 */
class Contrat
{
      
    /**
     * @var int
     *
     * @ORM\Column(name="id_contrat", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")

     */

private $idContrat ; 
    

    /**
     * @var int
     *
     * @ORM\Column(name="id_conducteur", type="integer", nullable=false)
          * @Assert\NotBlank(message="id conducteur should not be blank.")

     */
    private $idConducteur;
    
    /**
     * @var int
     *
     * @ORM\Column(name="id_admin", type="integer", nullable=false)
     * @Assert\NotBlank(message="id admin should not be blank.")
     */
    private $idAdmin;
   
    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_debut", type="date", nullable=false)
     *  @Assert\NotBlank(message="date  should not be blank.")
     */
    private $dateDebut;
   
    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_fin", type="date", nullable=false)
     * @Assert\NotBlank(message="date fin should not be blank.")
     */
    
    private $dateFin;
    
    /**
    * @var int
     *
     * @ORM\Column(name="prix", type="integer", nullable=false)
     *@Assert\NotBlank(message="prix should not be blank.")
     */
    private $prix;
    
    /**
     * @var string
     *
     * @ORM\Column(name="statut", type="string", length=50, nullable=false)
     * @Assert\NotBlank(message="statut  should not be blank.")
     */
    private $statut;

    /**
     * @var string
     *
     * @ORM\Column(name="qrCode", type="string", length=1000, nullable=false)
     * @Assert\NotBlank(message="qrcode should not be blank.")
     */
    private $qrCode;


    

    public function getIdContrat(): ?int
    {
        return $this->idContrat;
    }

    public function getDateDebut(): ?\DateTimeInterface
    {
        return $this->dateDebut;
    }

    public function setDateDebut(\DateTimeInterface $dateDebut): self
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    public function getDateFin(): ?\DateTimeInterface
    {
        return $this->dateFin;
    }

    public function setDateFin(\DateTimeInterface $dateFin): self
    {
        $this->dateFin = $dateFin;

        return $this;
    }

    public function getPrix(): ?int
    {
        return $this->prix;
    }

    public function setPrix(int $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getStatut(): ?string
    {
        return $this->statut;
    }

    public function setStatut(string $statut): self
    {
        $this->statut = $statut;

        return $this;
    }

    public function getIdAdmin(): ?int
    {
        return $this->idAdmin;
    }

    public function setIdAdmin(?int $idAdmin): self
    {
        $this->idAdmin = $idAdmin;

        return $this;
    }

    public function getIdConducteur(): ?int
    {
        return $this->idConducteur;
    }

    public function setIdConducteur(?int $idConducteur): self
    {
        $this->idConducteur = $idConducteur;

        return $this;
    }

    public function getQrCode(): ?string
    {
        return $this->qrCode;
    }

    public function setQrCode(string $qrCode): self
    {
        $this->qrCode = $qrCode;

        return $this;
    }


}
