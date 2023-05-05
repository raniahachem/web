<?php

namespace App\Validator;

use Symfony\Component\Validator\Constraint;

/**
 * @Annotation
 */
class Marque extends Constraint
{
    public $message = "The marque '{{ marque }}' is not allowed for type '{{ type }}'. Allowed values are: {{ allowed }}.";

    public $type;

    public $allowedValues;

    public function getRequiredOptions()
    {
        return ['type', 'allowedValues'];
    }

    public function validatedBy()
    {
        return MarqueTypeValidator::class;
    }
}
