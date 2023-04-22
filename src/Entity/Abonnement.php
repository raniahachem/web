<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;

use Doctrine\ORM\Mapping as ORM;

/**
 * Abonnement
 *
 * @ORM\Table(name="abonnement", uniqueConstraints={@ORM\UniqueConstraint(name="id_ab", columns={"idAb"})})
 * @ORM\Entity
 */
class Abonnement
{
    /**
     * @var int
     *
     * @ORM\Column(name="idAb", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
     private $idab;

    /**
     * @var string
     *
     * @ORM\Column(name="typeAb", type="string", length=255, nullable=false)
     * @Assert\NotBlank(message="TypeAB should not be blank.")
     */
    private $typeab;

    /**
     * @var float
     *
     * @ORM\Column(name="prixAb", type="float", precision=10, scale=0, nullable=false)
     *  @Assert\NotBlank(message="TypeAB should not be blank.")
     */
    private $prixab;

    /**
     * @var string
     *
     * @ORM\Column(name="modePaiementAb", type="string", length=255, nullable=false)
     *  @Assert\NotBlank(message="TypeAB should not be blank.")
     */
    private $modepaiementab;

    public function getIdab(): ?int
    {
        return $this->idab;
    }

    public function getTypeab(): ?string
    {
        return $this->typeab;
    }

    public function setTypeab(string $typeab): self
    {
        $this->typeab = $typeab;

        return $this;
    }

    public function getPrixab(): ?float
    {
        return $this->prixab;
    }

    public function setPrixab(float $prixab): self
    {
        $this->prixab = $prixab;

        return $this;
    }

    public function getModepaiementab(): ?string
    {
        return $this->modepaiementab;
    }

    public function setModepaiementab(string $modepaiementab): self
    {
        $this->modepaiementab = $modepaiementab;

        return $this;
    }


}
