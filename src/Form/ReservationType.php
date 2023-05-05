<?php

namespace App\Form;

use App\Entity\Conducteur;
use App\Entity\Offre;
use App\Entity\Client;



use App\Entity\Reservation;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;


class ReservationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nb_place')
            ->add('date')
            ->add('point_de_depart')
            ->add('point_arrive')
             ->add('id_client', EntityType::class, [
                'class' => Client::class,
                'choice_label' => 'id_client', // Remplacez "nom_complet" par le nom de l'attribut qui représente le nom complet du conducteur dans l'entité Conducteur
            ])
            ->add('id_conducteur', EntityType::class, [
                'class' => Conducteur::class,
                'choice_label' => 'id_conducteur', // Remplacez "nom_complet" par le nom de l'attribut qui représente le nom complet du conducteur dans l'entité Conducteur
            ])
             
            ->add('id_offre', EntityType::class, [
                'class' => Offre::class,
                'choice_label' => 'id', // Remplacez "nom_complet" par le nom de l'attribut qui représente le nom complet du conducteur dans l'entité Conducteur
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Reservation::class,
        ]);
    }
}
