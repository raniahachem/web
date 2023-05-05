<?php

namespace App\Form;

use App\Entity\Contrat;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\DateType;


class ContratType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $now = new \DateTime();
        $builder
            ->add('id_conducteur')
            ->add('id_admin')
            ->add('prix')
            ->add('date_debut', DateType::class, [
                'widget' => 'single_text',
                'data' => $now,
            ])
            ->add('date_fin', DateType::class, [
                // renders it as a single text box
                'widget' => 'single_text',
                'data' => $now,
            ])
            ->add('statut')
            ->add('qrCode')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Contrat::class,
        ]);
    }
}
