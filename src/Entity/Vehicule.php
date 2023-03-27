<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Vehicule
 *
 * @ORM\Table(name="vehicule", uniqueConstraints={@ORM\UniqueConstraint(name="immatriculation", columns={"immatriculation"})})
 * @ORM\Entity
 */
class Vehicule
{
    /**
     * @var string
     *
     * @ORM\Column(name="immatriculation", type="string", length=255, nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $immatriculation;

    /**
     * @var string
     *
     * @ORM\Column(name="type_du_vehicule", type="string", length=255, nullable=false)
     */
    private $typeDuVehicule;

    /**
     * @var string
     *
     * @ORM\Column(name="marque", type="string", length=255, nullable=false)
     */
    private $marque;

    /**
     * @var int
     *
     * @ORM\Column(name="cin_conducteur", type="integer", nullable=false)
     */
    private $cinConducteur;

    /**
     * @var int
     *
     * @ORM\Column(name="etat", type="integer", nullable=false)
     */
    private $etat;

    /**
     * @var int
     *
     * @ORM\Column(name="kilometrage", type="integer", nullable=false)
     */
    private $kilometrage;

    /**
     * @var string
     *
     * @ORM\Column(name="imageV", type="string", length=255, nullable=false)
     */
    private $imagev;


}
