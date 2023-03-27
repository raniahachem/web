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


}
