<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Livraison
 *
 * @ORM\Table(name="livraison")
 * @ORM\Entity
 */
class Livraison
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_livraison", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idLivraison;

    /**
     * @var string
     *
     * @ORM\Column(name="addresse_liv", type="string", length=255, nullable=false)
     */
    private $addresseLiv;

    /**
     * @var string
     *
     * @ORM\Column(name="destinataire", type="string", length=255, nullable=false)
     */
    private $destinataire;

    /**
     * @var float
     *
     * @ORM\Column(name="poids", type="float", precision=10, scale=0, nullable=false)
     */
    private $poids;

    /**
     * @var string
     *
     * @ORM\Column(name="contenu", type="string", length=255, nullable=false)
     */
    private $contenu;

    /**
     * @var float
     *
     * @ORM\Column(name="prix_liv", type="float", precision=10, scale=0, nullable=false)
     */
    private $prixLiv;


}
