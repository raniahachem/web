<?php

namespace App\Validator;

use Symfony\Component\Validator\Constraint;

/**
 * @Annotation
 */
class Immatriculation extends Constraint
{
    public $message = 'The immatriculation "{{ value }}" is not valid.';

    public function validatedBy()
    {
        return static::class.'Validator';
    }
}
