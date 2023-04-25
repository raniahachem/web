<?php

namespace App\Form;

use App\Entity\Reservation;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use App\Entity\Offre;
use App\Entity\Client;
use App\Entity\Conducteur;

class ReservationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nbPlace')
            ->add('date')
            /*->add('idClient', EntityType::class, [
                'class' => Client::class,
                'placeholder' => 'Client',
                'choice_label'=>'idClient',
                'multiple' => false,
                'expanded' => false
            ])*/
            ->add('idClient')
            ->add('pointDeDepart')
            ->add('pointArrive')
            ->add('idConducteur') 
            ->add('idOffre')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Reservation::class,
        ]);
    }
}
