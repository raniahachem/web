<?php

namespace App\Validator;

use Symfony\Component\Validator\Constraint;
use Symfony\Component\Validator\ConstraintValidator;

class MarqueValidator extends ConstraintValidator
{
    public function validate($value, Constraint $constraint)
    {
        /* @var $constraint \App\Validator\MarqueTypeConstraint */

        $vehicule = $this->context->getObject();
        $type = strtolower($vehicule->getTypeDuVehicule());
        $marque = strtolower($vehicule->getMarque());

        $allowedMarques = [];
        foreach ($constraint->marqueChoices as $key => $marques) {
            $allowedMarques[strtolower($key)] = array_map('strtolower', $marques);
        }

        if (array_key_exists($type, $allowedMarques) && !in_array($marque, $allowedMarques[$type])) {
            $this->context->buildViolation($constraint->message)
                ->setParameter('{{ marque }}', $value)
                ->setParameter('{{ type }}', $type)
                ->setParameter('{{ allowed }}', implode(', ', $allowedMarques[$type]))
                ->addViolation();
        }
    }
}
