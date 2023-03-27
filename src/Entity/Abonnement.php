<?php

namespace App\Entity;

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
     */
    private $typeab;

    /**
     * @var float
     *
     * @ORM\Column(name="prixAb", type="float", precision=10, scale=0, nullable=false)
     */
    private $prixab;

    /**
     * @var string
     *
     * @ORM\Column(name="modePaiementAb", type="string", length=255, nullable=false)
     */
    private $modepaiementab;


}
