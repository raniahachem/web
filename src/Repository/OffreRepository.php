<?php

namespace App\Repository;

use App\Entity\Offre;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

use Symfony\Component\Mime\Email;
 
use Symfony\Component\Mailer\MailerInterface;
use App\Service\MailerService;
use Doctrine\Common\Collections\Criteria;
use Symfony\Component\HttpFoundation\RequestStack;


/**
 * @extends ServiceEntityRepository<Offre>
 *
 * @method Offre|null find($id, $lockMode = null, $lockVersion = null)
 * @method Offre|null findOneBy(array $criteria, array $orderBy = null)
 * @method Offre[]    findAll()
 * @method Offre[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class OffreRepository extends ServiceEntityRepository
{
    
    public function __construct(ManagerRegistry $registry, RequestStack $requestStack)
    {
        parent::__construct($registry, Offre::class);
        $this->requestStack = $requestStack;
    }

    public function save(Offre $entity,MailerInterface $mailer,  bool $flush = false): void
    {
        $this->getEntityManager()->persist($entity);

 
        $email = (new Email())
        ->from('nour.benmiled@esprit.tn')
        ->to('ons.hamdi@esprit.tn')
        ->subject('Notification')
        ->html('<p> Votre offre a été publié avec succès '. $entity->getId().'</p>');

    $mailer->send($email);
    
    $session = $this->requestStack->getCurrentRequest()->getSession();
    $session->getFlashBag()->add(
        'success',
        '  avec succès'
       
    );


        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function save2(Offre $entity, bool $flush = false): void
    {
        $this->getEntityManager()->persist($entity);


        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function remove(Offre $entity, bool $flush = false): void
    {
        $this->getEntityManager()->remove($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }


    public function findByTypeVehicule($typeVehicule)
    {
        return $this->createQueryBuilder('o')
            ->andWhere('o.type_vehicule = :typeVehicule')
            ->setParameter('typeVehicule', $typeVehicule)
            ->getQuery()
            ->getResult();
    }

//    /**
//     * @return Offre[] Returns an array of Offre objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('o')
//            ->andWhere('o.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('o.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Offre
//    {
//        return $this->createQueryBuilder('o')
//            ->andWhere('o.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
