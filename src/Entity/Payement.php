<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Payement
 *
 * @ORM\Table(name="payement")
 * @ORM\Entity
 */
class Payement
{
    /**
     * @var int
     *
     * @ORM\Column(name="paymentId", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $paymentid;

    /**
     * @var int
     *
     * @ORM\Column(name="clientId", type="integer", nullable=false)
     */
    private $clientid;

    /**
     * @var string
     *
     * @ORM\Column(name="modePayment", type="string", length=255, nullable=false)
     */
    private $modepayment;

    /**
     * @var float
     *
     * @ORM\Column(name="prixCourse", type="float", precision=10, scale=0, nullable=false)
     */
    private $prixcourse;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="paymentDate", type="date", nullable=false)
     */
    private $paymentdate;


}
