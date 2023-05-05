<?php

namespace App\Form;

use App\Entity\Conducteur;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ConducteurType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('cin_conducteur')
            ->add('nom_conducteur')
            ->add('prenom_conducteur')
            ->add('telephone_conducteur')
            ->add('email_conducteur')
            ->add('ville_conducteur')
            ->add('mdp_conducteur')
            ->add('type_de_permis')
            ->add('image_conducteur')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Conducteur::class,
        ]);
    }
}
